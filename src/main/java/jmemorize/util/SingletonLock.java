/*
 * jMemorize - Learning made easy (and fun) - A Leitner flashcards tool
 * Copyright(C) 2021 Jonathan West
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 1, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package jmemorize.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Singleton lock is a thread-safe factory that is guaranteed to only create an object once, and all subsequent calls
 * will return the last created object.
 */
public final class SingletonLock<T> {

    private final Lock m_lock = new ReentrantLock();

    private final SingletonLockFactory<T> m_factory;

    private T m_object;

    public SingletonLock(SingletonLockFactory<T> factory) {
        this.m_factory = factory;
    }

    public T get() {
        try {
            m_lock.lock();

            if (m_object == null) {
                m_object = m_factory.build();
            }

            return m_object;

        } finally {
            m_lock.unlock();
        }

    }

    /** Returns the object to create; will be called 0 or 1 times, only. */
    public interface SingletonLockFactory<T> {
        public T build();
    }

}
