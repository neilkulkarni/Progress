HEADER
This program is written by Neil Kulkarni in May 2015.

DESCRIPTION
This program is a task manager designed to effectively organize your time and help reward you for not procrastinating. The program has three primary screens. The left-most is the list of tasks with the option to add new tasks as well as check them off. A task has an assignment date, a planned completion date, and a due date. The next screen is a calendar. The calendar can be viewed in a list of the tasks organized either by assignment date, planned completion date, or due date. The right-most screen is the progress. It tracks how effectively the planned completion date has been met. It rewards productivity and punishes procrastination. Progress is standardized by points leading to levels. Overall, the program is designed for students who seek more organization in their life and appreciate little rewards for improvement in their work ethic.

INSTRUCTIONS

The program launches in the calendar screen. To change to the other screens, the mouse should clicks onto the tabs labeled “Tasks”, “Calendar”, or “Progress”. In the “Tasks” screen, a list of tasks will be seen. Using JButtons, the tasks can be added. The check mark and x mark on the left of the task can be used to indicate a task has been completed or needs to be removed. In the “Calendar” screen, a full view of the added tasks will be seen in the perspective of the calendar. The mouse can be used to click on radio buttons that organize the list of tasks. The “Progress” screen simply shows a progress bar indicating level and points needed to advance levels. There is no interaction on this screen. 

FEATURES LIST

Must-Have:
1. Tasks screen, Calendar screen, and Progress screen
2. Ability to add and edit tasks on the tasks screen
3. Week view of the tasks scheduled in the Calendar screen
4. Rearrange calendar layout based on assignment date, planned completion date, or due date. 
5. Progress bar to indicate level and points needed to advance levels.

Want-to-Have:
1. Ability to rearrange tasks in the task screen based on assignment date, planned completion date, or due date.
2. Month view and daily view of the tasks scheduled in the Calendar screen.
3. Tasks can be edited in the Calendar screen and moved around by a drag-and-drop method
4. Intricate point system designed to reward higher for completion dates closer to assignment dates and further from due dates.
5. Priority levels offer point multiples and give more points but based on a user’s progress, the program may offer suggestions to rearrange tasks by priority.

Stretch Features:
1. Ability to share tasks with friends.
2. Add and track location data for each task.
3. Add reminder notifications for tasks that request notifications.
4. Ability to compete in points with friends and join them on joint productivity endeavors.
5. View-only access of the program through a web browser.

CLASS LIST
1. Progress: represents the main execution of the program (contains main method)
2. Frame: represents the window that has tabs to display the different panels
3. Task: represents one task and has a name and Dates to qualify its properties 
4. TaskList: represents the list of all the tasks added and the points and levels associated with the user
5. FileIO: saves the state of the task manager to maintain tasks, points, and levels even if program is closed
6. TaskPanel: represents the screen on which tasks can be viewed 
7. CalendarPanel: represents the screen on which tasks can be arranged by assignment date, planned date, or due date
8. ProgressPanel: represents the screen on which the progress can be viewed
9. ControlPanel: represents and manages the listeners interaction between screens
10. ControlListener (interface): helps set constants to represent the radio button selections

RESPONSIBILITY LIST
I, Neil Kulkarni, will be the sole creator of this program and am responsible for the creation, execution, and monetization of it. Others may be consulted for inspiration, assistance, and suggestions.
