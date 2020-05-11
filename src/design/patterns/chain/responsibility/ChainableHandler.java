/*
 * MIT License
 *
 * Copyright (c) 2020 Isaac Ramirez
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package design.patterns.chain.responsibility;

/**
 * Implementation of Chain of Responsibility pattern
 * To learn more about this pattern refer to:
 *
 * https://refactoring.guru/design-patterns/chain-of-responsibility
 */
public abstract class ChainableHandler<T> {
    private ChainableHandler<T> chain;
    private ChainableHandler<T> lastLink;

    public ChainableHandler<T> withNext(ChainableHandler<T> next) {
        if (this.chain == null) {
            this.chain = next;
        } else {
            this.lastLink.withNext(next);
        }
        this.lastLink = next;
        return this;
    }

    public boolean check(T request) {
        if (this.doCheck(request)) {
            return this.checkNext(request);
        }
        return false;
    }

    protected abstract boolean doCheck(T request);

    private boolean checkNext(T request) {
        return chain != null ? this.chain.check(request) : true;
    }
}