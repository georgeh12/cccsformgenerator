# Introduction #

This can be a real pain, so I have step-by-step instructions for installing SVN to Netbeans, and checking out the googlecode.


# Details #

1. Download and install Netbeans (6.5 or >) and Collabnet's subversion. Under the download tab (look up) you will find the subversion software.

(1.5). Download the init\_svn.bat file into your SVN installation location. Run it, and enter your NetBeans repository location into the box. This will tell SVN where your project is, so the folder will look something like: My Documents\NetBeansProjects.

2. Open Netbeans and click the menu options "Tools > Options". Click the "Miscellaneous" tab at the top. Click the "Versioning" tab under that. Select "Subversion" from the list on the left. For the text box labeled "Path to SVN executable File" enter "C:\Program Files\CollabNet Subversion Client", or wherever you installed the SVN client. Click "OK", that step is complete.

3. Now click the menu options "Team > Subversion > Checkout...". For the text box labeled "Repository URL" enter "https://cccsformgenerator.googlecode.com/svn". In the text box labeled "User", enter your FULL googlecode account, with the "@gmail.com" suffix. For the text box labeled "Password" enter the password found at the googlecode site, here: "http://code.google.com/p/cccsformgenerator/source/checkout" and click "When prompted, enter your generated googlecode.com password". Now click "Next", choose a location to save the project, and click "Finish".

4. After making changes to project, commit by right-clicking the project in the "Projects" tab in Netbeans, then choose "Subversion > Commit". When dialog appears, just choose "Commit".