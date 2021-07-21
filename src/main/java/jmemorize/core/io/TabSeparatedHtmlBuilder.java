package jmemorize.core.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import jmemorize.core.Card;
import jmemorize.core.CardSide;
import jmemorize.core.Category;
import jmemorize.core.Lesson;
import jmemorize.core.Main;

public class TabSeparatedHtmlBuilder {

    @SuppressWarnings("unused")
    private static Logger logger;

    public static void export(Lesson lesson, Path file) throws IOException {
        logger = Main.getLogger();

        List<String> allCards = new ArrayList<>();

        // add cards in subtrees
        List<Category> subtree = lesson.getRootCategory().getSubtreeList();
        for (Category category : subtree) {

            List<String> cardsInCategory = writeCategory(category);
            allCards.addAll(cardsInCategory);
        }

        String output = allCards.stream().map(a -> a + "\n").reduce((a, b) -> a + b).get();

        Files.writeString(file, output);

    }

    private static List<String> writeCategory(Category category) {

        List<String> res = new ArrayList<>();

        // ignore empty categories
        if (category.getLocalCards().size() == 0) {
            return res;
        }

        for (Card card : category.getLocalCards()) {

            CardSide front = card.getFrontSide();
            CardSide back = card.getBackSide();

            String line = convertCardSide(front) + "\t" + convertCardSide(back);
            res.add(line);
        }

        return res;

    }

    private static String convertCardSide(CardSide side) {

        return Arrays.asList(side.getText().getFormatted().trim().split("\\r?\\n")).stream().map(line -> {

            line = line.replace("\t", "    ");
            line = line.replace("  ", " &nbsp;");
            line = line + "<br>";

            return line;

        }).reduce((a, b) -> a + b).map(a -> {
            if (a.endsWith("<br>")) {
                a = a.substring(0, a.length() - 4);
            }
            return a;
        }).get();

    }
}
