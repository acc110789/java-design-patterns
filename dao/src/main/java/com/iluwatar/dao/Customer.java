/**
 * The MIT License
 * Copyright (c) 2014 Ilkka Seppälä
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.dao;

/**
 *
 * Customer
 *
 */
public class Customer {

    private int id;
    private String firstName;
    private String lastName;

    /**
     * Constructor
     */
    public Customer(final int id, final String firstName, final String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + getId() + ", firstName='" + getFirstName() + '\'' + ", lastName='"
                + getLastName() + '\'' + '}';
    }

    @Override
    public boolean equals(final Object o) {
        boolean isEqual = false;
        if (this == o) {
            isEqual = true;
        } else if (o != null && getClass() == o.getClass()) {
            final Customer customer = (Customer) o;
            if (getId() == customer.getId()) {
                isEqual = true;
            }
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
