# Inspire
An Android app for setting goals

This app is used for setting goals by giving the name of the goal and the date by which it should be completed.

The goals are stored in an SQL database and displayed using RecyclerView with colors assigned to them randomly at the time of creation of goal.
They can be marked as complete with a long click on the goal card, which turns the color to grey and moves them to the bottom of the list.
There are pages for creating editing texts that implements Timepicker and Datepicker.
The editing page for the goals can be opened by clicking on a card and a FloatingActionButton on the main page opens up the page for adding a new goal.

I have a few more features in mind and will be implementing them when I get the time:

1) Working night mode
2) Stats page which shows a score based on goals completed for a push to do more
3) Reminders for goals
4) A notes section for jotting down quick notes
