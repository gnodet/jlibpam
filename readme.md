![JLibPam][1]
==========

Description
-----------

[JLibPam][2] is a small java library that allows accessing the Pluggable Authentication Modules (PAM) layer on Unix based operating systems.

Features
--------

* Provides full access to the PAM layer.
* Easy to use interface.

Synopsis
--------

Most of Java based applications to not integrate with the Operating System security layer.  However, in certain cases, you may need to do so.
This library will allow accessing the [PAM][3] layer wich is used nearly all Unix systems.

Example Usage
-------------

Authenticating a user can be done using the following code:

    org.fusesource.jlibpam.Pam.authenticate("system", "user", "password");

In addition to those easy to use high level methods, you can also access lower level methods if required.    

Project Links
-------------

* [Project Home][2]
* [Release Downloads](http://jlibpam.fusesource.org/downloads/index.html)
* [GitHub](http://github.com/gnodet/jlibpam/tree/master)
* Source: `git clone git://forge.fusesource.com/jlibpam.git`
* [Issue Tracker](http://fusesource.com/issues/browse/JLIBPAM)
* [Mailing Lists](http://fusesource.com/forge/projects/JLIBPAM/mailing-lists)

[1]: http://jlibpam.fusesource.org/images/project-logo.png "JLibPam"
[2]: http://jlibpam.fusesource.org/ "JLibPam"
[3]: http://en.wikipedia.org/wiki/Pluggable_Authentication_Modules "Wikipedia"
