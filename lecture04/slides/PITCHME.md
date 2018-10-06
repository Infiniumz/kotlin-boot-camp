---
<!-- .slide: class="center" -->
@title[Kotlin]

### Lecture 4
#### Advanced Kotlin #1


---
@title[Sign in]
<!-- .slide: class="center" -->

Please [**sign in**](https://sphere.mail.ru/)
 
*For off-line students only*


---
@title[Get ready]
<!-- .slide: class="center" -->
```bash
git fetch upstream
git checkout -b lecture04 upstream/lecture04
cd lecture04
```

open as new project


---
@title[Agenda]
1. @css[highlight](Exceptions)
1. Generics
1. Conventions
1. Introduction to web 


---
@title[Problem]
### Anything that can go wrong will go wrong
- lost connection
- inconsistent object state
- out of memory
- file not found


---
@title[Recovery]
#### Recovery
- reconnect
- fix/rebuild object
- free some memory
- create new file

or
- cancel operations


---
@title[Exception]
<!-- .slide: class="center" -->

```kotlin
throw Exception("Hello, World!")
```


---
@title[Exceptions. Unchecked]
<!-- .slide: class="center" -->
All Exceptions in Kotlin are @css[highlight](unchecked)


---
@title[Exceptions. Unchecked. Why?]
<!-- .slide: class="center" -->
[Java's checked exceptions were a mistake](http://radio-weblogs.com/0122027/stories/2003/04/01/JavasCheckedExceptionsWereAMistake.html) *Rod Waldhoff*

[The Trouble with Checked Exceptions](https://www.artima.com/intv/handcuffs.html) *Anders Hejlsberg*



---?code=lecture04/src/test/kotlin/io/rybalkinsd/kotlinbootcamp/ExceptionHandlingTest.kt&title=Exceptions. Handling
@[9-19](try - catch - finally)
@[24-35](try - catch - catch)
@[40-46](try expression)
@[41, 45](return values of different branches)
@[51](`?: throw`)


---
@title[Exceptions. Practice]
<!-- .slide: class="center" -->
Implement functions in `io.rybalkinsd.kotlinbootcamp.practice.binaryUtil.kt`

Fix all tests in `io.rybalkinsd.kotlinbootcamp.practice.BinaryUtilTest.kt`


---?code=lecture04/src/main/kotlin/io/rybalkinsd/kotlinbootcamp/practice/binaryUtil.kt&title=Exception. Practice
@[3-9]()


---
@title[Agenda]
1. Exceptions
1. @css[highlight](Generics #1)
1. Conventions
1. Introduction to web 


---?code=lecture04/src/main/kotlin/io/rybalkinsd/kotlinbootcamp/generics.kt&title=Generics. #1

---
@title[Generics. *variance]
<!-- .slide: class="center" -->
[Official docs](https://kotlinlang.org/docs/reference/generics.html)

More content in next lectures


---
@title[Agenda]
1. Exceptions
1. Generics #1
1. @css[highlight](Conventions)
1. Introduction to web 


---
@title[Conventions. Operators]

| Expression          | Function name             |
| ------------- | -----------------:|
| a * b       | times        |
| a / b       | div        |
| a % b       | mod        |
| a + b       | plus        |
| a - b       | minus        |

---
@title[Conventions. `+=`]
<!-- .slide: class="center" -->
`*Assign`

```kotlin
operator fun Point.plusAssign(element: Point): Unit {
    ...
}
```

---
@title[Conventions. Operators. Unary]
<!-- .slide: class="center" -->
```kotlin
operator fun Point.unaryMinus() = Point(-x, -y)
```


---
@title[Conventions. Operators. Unary]

| Expression          | Function name             |
| ------------- | -----------------:|
| +a       | unaryPlus        |
| -a       | unaryMinus        |
| !a       | not        |
| ++a, a++       | inc        |
| --a, b--       | dec        |


---
@title[Conventions. Operators. Comparison]
<!-- .slide: class="center" -->

`a == b  --->  a?.equals(b) ?: b == null`


---
@title[That's all for today]

Please leave your @css[highlight](feedback).
 
*Offline students will get the link via email*