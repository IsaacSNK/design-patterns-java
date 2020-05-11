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
package design.patterns.chain.responsibility.example;

/**
 * Example of the Chain of responsibility pattern applied to
 * a chain of user validations. Consider the case where
 * an user id must be validated through several stage
 * before approving/rejecting its access to the system.
 *
 * The chain will be like:
 *
 * 1. Check of the user is blacklisted, if so, is rejected, if not,
 *    the next step in the chain is called to validate more
 * 2. Check if the user has the needed roles to use the system
 * 3. Check if the user is not already logged in the system
 */
public class UserValidationChain {
    public static void main(String[] args) {
        new BlackListHandler()
            .withNext(new UserRolesHandler())
            .withNext(new UserConcurrentSessionHandler())
            .check("userIdToCheck");
    }
}