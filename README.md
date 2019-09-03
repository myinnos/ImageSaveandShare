# Image Save and Share
Library to save image locally and shows options to open and share !

 ![ImageSaveandShare - Example1](https://raw.githubusercontent.com/myinnos/ImageSaveandShare/master/gif/demo-gif.gif)

#### Download Demo APK from [HERE](https://github.com/myinnos/ImageSaveandShare/raw/master/apk/SaveBitmapAndShare-Demo.apk "APK")
  
#### Kindly use the following links to use this library:

In build.gradle (Project)
```java
allprojects {
  repositories {
			...
		maven { url "https://jitpack.io" }
	}
}
```
And then in the other gradle file(may be your app gradle or your own module library gradle, but never add in both of them to avoid conflict.)
```java
dependencies {
    // AppCompat version
        implementation 'com.github.myinnos:ImageSaveandShare:1.0.3'
	or
    // AndroidX version
        implementation 'com.github.myinnos:ImageSaveandShare:1.0.4'
}
```
How to use
-----
Implementing save and share function : [#Example](https://github.com/myinnos/ImageSaveandShare/blob/master/app/src/main/java/in/myinnos/savebitmapandshare/MainActivity.java "Example")
```java
SaveAndShare.save(this,
               image, // bitmap image
               null, // name for image
               null, // title for bottom popup 
               null // message for bottom popup
       );
```
##### Any Queries? or Feedback, please let me know by opening a [new issue](https://github.com/myinnos/ImageSaveandShare/issues/new)!

## Contact
#### Prabhakar Thota
* :globe_with_meridians: Website: [myinnos.in](http://www.myinnos.in "Prabhakar Thota")
* :email: e-mail: contact@myinnos.in
* :mag_right: LinkedIn: [PrabhakarThota](https://www.linkedin.com/in/prabhakarthota "Prabhakar Thota on LinkedIn")
* :thumbsup: Twitter: [@myinnos](https://twitter.com/myinnos "Prabhakar Thota on twitter")   
* :camera: Instagram: [@prabhakar_t_](https://www.instagram.com/prabhakar_t_/ "Prabhakar Thota on Instagram")   

> If you appreciate my work, consider buying me a cup of :coffee: to keep me recharged :metal: by [PayPal](https://www.paypal.me/fansfolio)

License
-------

    Copyright 2017 MyInnos

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
