package jmemorize.gui.swing.panels;

import javax.swing.JPanel;

import jmemorize.core.CardSide;
import jmemorize.core.FormattedText;
import jmemorize.gui.swing.CardFont;
import jmemorize.gui.swing.Quiz;

public class TypeInQuiz implements Quiz {
    private CardSidePanel m_cardSide;
    private FormattedText m_text;

    @Override
    public void showQuestion(CardSide answerCardSide) {
        m_text = answerCardSide.getText(); // TODO add images support
        m_cardSide.setText(FormattedText.EMPTY);
    }

    @Override
    public float showAnswer() {
        String actual = m_cardSide.getText().getUnformatted();
        String expected = m_text.getUnformatted();

        m_cardSide.setText(m_text);

        if (expected.equalsIgnoreCase(actual))
            return 1f;

        return -1f;
    }

    @Override
    public JPanel getVisual() {
        m_cardSide = new CardSidePanel();
        return m_cardSide;
    }

    @Override
    public String getHelpText() {
        return "Type in the answer";
    }

    @Override
    public void setQuestionFont(CardFont font) {
    }

    @Override
    public void setAnswerFont(CardFont font) {
    }
}
