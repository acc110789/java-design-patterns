/**
 * The MIT License
 * Copyright (c) 2014 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.property;

import com.iluwatar.property.Character.Type;

/**
 * 
 * The Property pattern is also known as Prototype inheritance.
 * <p>
 * In prototype inheritance instead of classes, as opposite to Java class inheritance, objects are
 * used to create another objects and object hierarchies. Hierarchies are created using prototype
 * chain through delegation: every object has link to parent object. Any base (parent) object can be
 * amended at runtime (by adding or removal of some property), and all child objects will be
 * affected as result.
 * <p>
 * In this example we demonstrate {@link Character} instantiation using the Property pattern.
 * 
 */
public class App {

  /**
   * Program entry point
   * 
   * @param args command line args
   */
  public static void main(String[] args) {
    /* set up */
    Prototype charProto = new Character();//所有职业的原型
    charProto.set(Stats.STRENGTH, 10);
    charProto.set(Stats.AGILITY, 10);
    charProto.set(Stats.ARMOR, 10);
    charProto.set(Stats.ATTACK_POWER, 10);

    Character mageProto = new Character(Type.MAGE, charProto);//法师的原型
    mageProto.set(Stats.INTELLECT, 15);
    mageProto.set(Stats.SPIRIT, 10);

    Character warProto = new Character(Type.WARRIOR, charProto);//战士的原型
    warProto.set(Stats.RAGE, 15);
    warProto.set(Stats.ARMOR, 15); // boost default armor for warrior

    Character rogueProto = new Character(Type.ROGUE, charProto);//盗贼的原型
    rogueProto.set(Stats.ENERGY, 15);
    rogueProto.set(Stats.AGILITY, 15); // boost default agility for rogue

    /* usage */
    Character mag = new Character("Player_1", mageProto);
    mag.set(Stats.ARMOR, 8);
    System.out.println(mag);

    Character warrior = new Character("Player_2", warProto);
    System.out.println(warrior);

    Character rogue = new Character("Player_3", rogueProto);
    System.out.println(rogue);

    Character rogueDouble = new Character("Player_4", rogue);
    rogueDouble.set(Stats.ATTACK_POWER, 12);
    System.out.println(rogueDouble);
  }
}
