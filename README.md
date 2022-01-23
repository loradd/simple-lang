# Simple Programming Language

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/loradd/simple-lang)

This repository contains the implementation of an [ANLTR](https://www.antlr.org/) parser for a simple programming
language named **Simple**. The project can be built and tested using the generated [Gradle](https://gradle.org/)
wrapper, while a basic [Clikt](https://ajalt.github.io/clikt/) command line interface is available for interacting with
the parser and lexer.

#### Language

_Simple_ programs consist in _workspaces_ containing _functions_ and _statements_. If present, statements are
sequentially executed after all function definitions have been analyzed. The language supports a basic set of
expressions, i.e. binary (logical, relational, additive and multiplicative)
and unary (logical and arithmetic negation). Function invocations, variable references and numeric literals complete the
set of supported expression kinds.

_Functions_ have zero or more parameters and a sequence of body statements. These last ones can be of five different
kinds: _Return_, _Print_, _Conditional_, _Binding_ and _Expression_ statements.

_Return Statements_ terminate the execution of their container function and return back a value:

```
return <expression>;
```

_Print Statements_ print out a value in the standard output stream: `print <expression>;`

```
print <expression>;
```

_Conditional Statements_ allow users to branch and execute specific statements based on the outcome of one or more
conditions:

```
if (<expression>) {
    <statements>
} else if (<expression>) {
    <statements>   
} else {
    <statements>
}
```

_Binding Statements_ allow to associates names to values, i.e. assignments:

```
<ID> = <expression>;
```

## Building

The project can be built using the generated [Gradle](https://gradle.org/) wrapper from the root path, as follows:

```
./gradlew build
```

## Testing

Unit tests are defined using [JUnit5](https://junit.org/junit5/) and can be executed using the
generated [Gradle](https://gradle.org/) wrapper from the root path, as follows:

```
./gradlew test
```

## Command-Line Interface

The _simple_ script file in the project root directory provides users a basic command-line interface application
developed using [Clikt](https://ajalt.github.io/clikt/). Given an input file, two commands are available:

* `tokens`: prints out the corresponding token list
* `tree`: prints out the corresponding parse tree

The parser entry rule can be specified through one of the options among:

* `--workspace`: start from the _Workspace_ parser rule;
* `--function`: start from the _Function_ parser rule;
* `--statement`: start from the _Statement_ parser rule;
* `--expression`: start from the _Expression_ parser rule;

For example, the following command will print out the parse tree obtained from the content
in `additive_expression.simple`:

```
./simple parse tree --expression src/test/resources/expressions/additive_expression/additive_expression.simple
```
