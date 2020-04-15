# fictional-journey

A repository for the SENG 300 Group Project

To run our code, you will need to first clone the repository from GitHub. 

After this, go into Eclipse, and perform the following:

File > Open Projects from File System... > now, navigate to the cloned repository, and **select the folder labelled "backend_draft".**

Hit "Finish" in the corner of the import screen. 

At this point, it will look like our code is trash and a bunch of errors will pop-up - this is because the JavaFX user library has not yet been added.

To fix this, right click on the project: Build Path > Add Libraries.. > now, select User Library and hit Next. 

Select your installed JavaFX user library, and hit Finish. 

The errors should now resolve. 

You will want to then navigate to the class labelled GUI and run this, since it's main is the core of the application. 

If JavaFX runtime components are missing, please hit the dropdown next to the run and create a run configuration for GUI with the virtual machine arguments:

--module-path {path to your JavaFX installation} --add-modules javafx.controls,javafx.fxml

Ensure that the "Use the -XstartOnFirstThread argument when launching with SWT" option is disabled / unchecked.
(make sure to change the path to fit your computer)

At this point you should be able to run the project.

To see it in all of it's glory (not a whole lot at the moment), you will then want to select Doctor and sign is as me (Dylan L):

Username (top): "dylan"
Password (bottom): "1"

Once you click Login, it will take you to the home GUI for a doctor. 

Additionally, you may sign in as a patient by selecting Patient, and using the following credentials:

Username (top): "adam"
Password (bottom): "1"


This will log you into a patient named Adam.

For a doctor, you can sign into: 

Username: "john"
Password: "1"

This will log you into a Doctor named John.

You can book tests and do other data operations by going into the control panel in the bottom 
Tests and other bookings attributed to the user would show up in either of the lists. 

There are some doctors in physiotherapy, nutrition and family to book appointments with.

Doctors can recommend tests to patients they have helped or will help in the past/future 2 months.

For now, that's really all the functionality we have. In the final iteration, we intend to make appointments and tests bookable from the GUI, and implement the tableview to actually display appointments and other bookings (along with the other items in our product backlog). 

To create additional users and such, you can go into the Admin control panel and use the GUI elements within it to create Doctors, etc as shown in our video.


Thank you for reviewing our project! 

- Dylan & the rest of the Sukrum Technologies team.
