#Android BCrypt

source: https://github.com/patrickfav/bcrypt

latest version: v0.9.0

implementation group: 'at.favre.lib', name: 'bcrypt', version: '{latest-version}'
implementation group: 'at.favre.lib', name: 'bcrypt', version: '0.9.0'

in manifest use
android:theme="@style/Theme.AppCompat.Light"
instead of
android:theme="@style/Theme.AndroidBCrypt"

tested on Android 21 and 30

Deprecated Gradle features were used in this build, making it incompatible with Gradle 8.0. Use '--warning-mode all' to show the individual deprecation warnings

run in Terminal:
./gradlew build --warning-mode all