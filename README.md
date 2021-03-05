# icecream-java

[![Java CI with Maven](https://github.com/Akshay-Thakare/icecream-java/actions/workflows/maven.yml/badge.svg)](https://github.com/Akshay-Thakare/icecream-java/actions/workflows/maven.yml)

icecream-java is a Java port of the [icecream](https://github.com/gruns/icecream) library for Python.

## Installation
> Download lastest jar file from release and add to your project

## Usage

### Import Tricks

Static import `import static org.icecream.IceCream.ic;` for ease of use.

### Inspect Execution

```
ic()
```

Prints
```
ic | org.icecream.IceCreamTest > simplePrintTest:L24
```


### Inspect Variables

```
ic(1, 2, 3);
ic(concat("1", "2"));

String concat(String a, String b) {
  return a + b;
}
```

Prints
```
ic | org.icecream.IceCreamTest > withParamsTest:L25 > param_0 = 1 | param_1 = 2 | param_2 = 3
ic | org.icecream.IceCreamTest > withMethodAsParamTest:L33 > param_0 = 12
```

### Configuration

#### Change print prefix

```
IceCream.setPrefix("icc |");
```

#### Disable print context

This will disable printing of file name, class name, method name and line number.
```
IceCream.includeContext(false);
```

#### Disable printing of specific parts of the context

```
IceCream.includeFilename(false);
IceCream.includeClassName(false);
IceCream.includeMethodNameAndLineNumber(false);
```
