To use the DataManager Package:

Add these lines to you android project gradle and app gragle files.

**Project Gradle**
```xml
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' } //add this line
		}
	}
```
**App Gradle**
```xml
dependencies {
          ...
	        implementation 'com.github.SeanfvdM:Manager:0.0.2' //add this line
          ...
	}
```

To start accessing the Library:
This is if you want to use the manager class which will provide more functionality at a later date.
1) Create a manager instance
```java
Manager<ReaderWriter> manager = new Manager<ReaderWriter>();
```
2) Initialise the base type
**Requires manual permission check**
```java
manager.init(new ReaderWriter());
manager.get().setMain(getFilesDir());
```
**Will check for permissions**
```java
manager.init(new ReaderWriter(),this);
manager.get().setMain(getFilesDir());
```

3) Access the base type methods
```java
manager.get().
```

You can also access the Base types driectly.
1) Create an instance
```java
ReaderWriter rw = new ReaderWriter();
```
2) Initialise the base type
```java
rw.setMain(getFilesDir());
```
3)Check for permission
```java
rw.checkPermissions(this);
```

