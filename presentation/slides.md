# Java Crash Course
## By Per-Arne Andersen

---

<div class="row">

<div class="col-sm-6 left-align">

### Content
Part 0
- Introduction

Part 1
- Variables
- Primitives
- IF statements

Part 2
- Loops
- Strings
- Arrays

Part 3
- Classes
- Objects
- Inheritance
</div>

<div class="col-sm-6">

* Will cover skills required to complete the course Assignments

* Please ask for clarification if you find anything unclear

* Three two-hour lectures

* http://mortengoodwin.net/Public/DAT113/

* This is a **crash-course** in **java**. You are expected to **ask questions** if you dont fully understand a concept

</div>

</div>


---

# Part 0

---

### Installation (Java JDK)
(We assume windows for this part. But there is similar steps for Mac and Windows. Google it)

1. Download the latest Java JDK from (Google it)
https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

2. Install it.
<img src="https://www.guru99.com/images/java/1912211_install_javaimage3.jpg">

---

### Installation (Intellij IDEA)
1. Download Intellij IDEA Ultimate or Community (Ultimate is free for students) https://www.jetbrains.com/idea/download

2. Install IntelliJ IDEA

3. Start IntelliJ and create new java project

4. Open the Project Tab and expand the project. Right click the `src` directory and create a new `java class` called `Main.java`
```java
public class Main {

    public static void main(String[] args){

        System.out.println("Hello World!");

    }
}
```

5. Right click the `Main.java` file and click `Run Main.main()` (or `CTRL + SHIFT + F10`)

6. Congratulations. Your first java application!

---

### Variables
https://www.javatpoint.com/java-variables

---
- What is variable? <!-- .element: class="fragment"  -->
- Name of reserved area allocated in memory <!-- .element: class="fragment"  style="color:red"-->

![](https://static.javatpoint.com/core/images/variable.png) <!-- .element: class="fragment"  -->

Example: <!-- .element: class="fragment"  -->  
```java
int data = 50; // We store 50 in memory with the variable name 'data'
```  
<!-- .element: class="fragment"  -->  

---

### Types of variables

- Local variable
- Instance variable
- Static Variable

---

![](https://i.imgur.com/Lzp7Mxa.png)

---

### Code Example
```java
class A {
    int variableInstance = 42; // I'm a INSTANCE variable
    static int variableStatic = 99; // I'm a STATIC variable

    public void getSomething(){
        int variableA = 10; // I'm a LOCAL variable
    }
    
}

class B {

    public void getACompletlyDifferentThing(){
    
        int variableInstance = 19; // I'm a LOCAL variable
        
        // We access the STATIC variable from class A.
        variableInstance = variableInstance + A.variableStatic; 

    }
}
```


---

### To Summarize


<div class="fragment">

**Local Variable:** <!-- .element: class="left-align "  -->
- A variable declared **inside the body** of the method is called **local variable**. You can use this variable only within **that method** and the other methods in the class aren't even **aware** that the variable exists.

</div>

<div class="fragment">

**Instance Variable:** <!-- .element: class="left-align "  -->
- A variable declared **inside the class** but outside the body of the method, is called instance variable. It is **not declared as static**.
- It is called instance variable because its value is instance specific and is **not shared among instances**.
- Example: The value of a speedometer in a car is **only stored** in that specific car "instance"
- More on this when we touch upon classes
</div>

---

### To Summarize

**Static Variable:** <!-- .element: class="left-align "  -->
- It cannot be local. You can create a **single copy** of static variable and **share** among all the instances of the class. Memory allocation for static variable **happens only once** when the class is loaded in the memory.

---

### Primitives
https://www.javatpoint.com/java-data-types

![](https://static.javatpoint.com/images/java-data-types.png)

---

### Primitives

| Data Type | Default Value | Default size |
|-----------|---------------|--------------|
| boolean   | false         | 1 bit        |
| char      | '\u0000'      | 2 byte       |
| byte      | 0             | 1 byte       |
| short     | 0             | 2 byte       |
| int       | 0             | 4 byte       |
| long      | 0L            | 8 byte       |
| float     | 0.0f          | 4 byte       |
| double    | 0.0d          | 8 byte       |


**Important to get right when working with numbers**

---

### If Statements
https://www.javatpoint.com/java-if-else

![](https://static.javatpoint.com/images/core/if1.png)

---

### If Statements

```java
//Java Program to demonstate the use of if statement.  
public class IfExample {  
public static void main(String[] args) {  
    //defining an 'age' variable  
    int age=20;  
    //checking the age  
    if(age>18){  
        System.out.print("Age is greater than 18");  
    }  
}  
}  
```
Output:

    - Age is greater than 18

---

# Part 2

Loops, Strings and Arrays
---


