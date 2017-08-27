# What is KIPHY?
KIPHY is a simple app that shows animated GIFs from GIPHY.com. This app was develop in my early days of Kotlin learning. It was written 100% in Kotlin, using:

.Model-View-Presenter (MVP) presentation architecture pattern
.Glide 4 for displaying animated GIFs
.Retrofit 2 for REST API calls
.Gson for parsing JSON objects from GIPHY API endpoints
.Material Design Support Library
.Android Studio 3.0 Preview (w/ Kotlin built-in support)

# Cloning the repo
## Using command line
Open a terminal and type:

    git clone https://github.com/ramonrabello/Kiphy.git

## In Android Studio 3.0 Preview
1. First download the latest preview version of Android Studio from https://developer.android.com/studio/preview/install-preview.html.
2. Go to File > New... > Project from Version Control > GitHub
3. Fill _host_, _Auth type_, _Login_ and _Password_ fields from your GitHub Account. You also can sign up you do not have any a GitHub account.
4. Click _Login_ and wait for the Android Studio finishes the project creation.

# Integrating with GIPHY API
## Creating a GIPHY developers account
In order to call GIPHY API, you primarily need a GIPHY developers account. To do so, follow the steps:
1.Go to developers.giphy.com
2.Click on _Create an App_ button
3.Type a _Name_ and a _Description_ for your app.
4.Copy the Api Key

## Configuring build.gradle with GIPHY Api Key
Open the _build.gradle_ (app module), search for GIPHY_API_KEY configuration field and paste the generated API KEY that you have previously copied. Your _build.gradle_ must be something like code below:

     buildTypes {
        debug {
            buildConfigField "String", '"GIPHY_API_KEY"','"{PASTE_YOUR_API_KEY_HERE}"'
        }
     }
     
Now sync your project with gradle build files and that's it! Now you can run KIPHY in some AVD ou real devices.
     
  
 
