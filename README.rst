About
=====

Small app to show Accelerometer/Magnetometer data. Useful for testing or as starting code.


Build
=====

1. Clone the git repository::

    $ git clone https://github.com/tlatsas/sensors-test-android.git

2. Create the project environment::

    $ android update project --path sensors-test-android/

3. Navigate into the project folder and compile in debug mode::

    $ cd ensors-test-android & ant debug


Install
=======

* Install in default running emulator using `ant`

    `$ ant installd`

* Install in default running emulator using `adb`

    `$ adb -e install -s bin/sensors_test-debug.apk`

* You can combine compile and installation using `ant`

    `$ ant debug install`

* Install in default device

    `$ adb -d install -s bin/sensors_test-debug.apk`



Screenshot
==========

.. image:: https://github.com/tlatsas/sensors-test-android/wiki/img/sensors-viewer-1.png
    :alt: sensors test screenshot
