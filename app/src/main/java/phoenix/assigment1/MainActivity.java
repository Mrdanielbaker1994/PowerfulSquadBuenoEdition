/*------------------------------------------------------------------
 * FiLE: MainActivity.java
 * VERSION: 03/Feb/2019
 * PROGRAMMERS:
 * Edgar Elias - 7515927
 * Daniel Baker - 6461982
 * Irina Alexandru - 7925423
 * Samuel Lyra - 7763881
 *
 * DESCRIPTION: This is the Main Activity of the application. The user
 *              will be able to visualize and add courses. Moreover,
 *              the user will also be able to select a course so that
 *              he can see its assignments.
 * -----------------------------------------------------------------*/

package phoenix.assigment1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity
{
    /*This object will keep track of all the current courses*/
    ArrayList<Course> myCourses = new ArrayList<Course>();


    /*----------------------------------------------------------
     * Function: onCreate()
     * Parameters: Bundle savedInstanceState
     * Description: This method will process any incoming intents
     *              from previous activities and start an event
     *              listener for the spinner in case a course
     *              gets selected.
     * Returns: Void
     * --------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mainThreadChangePicture();

        new Thread(new threadClass(this)).start();

 /*       testingClass t = new testingClass(this);
        t.test();*/

        /*This function will add all the current courses in the spinner*/
        processIntents();

        /*Create an event listener when an item in the Spinner gets selected*/
        Spinner coursesSpinner = (Spinner) findViewById(R.id.coursesSpinnerID);
        coursesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0)
                {
                    try
                    {
                        //Toast.makeText(parent.getContext(), "Selected Item: " + position, Toast.LENGTH_SHORT).show();
                        transferToAddTasks(position);
                    } catch (Exception e)
                    {
                        Log.d("MainActivity", "Something went wrong when selecting a course in the Spinner - " + e.toString());
                    }
                }
            }




            @Override
            public void onNothingSelected(AdapterView<?> parent) { /*Do nothing*/ }

        }); //end of listener
    } //end of onCreate()


    /*----------------------------------------------------------
    * Function: addBtnClick()
    * Parameters: View view
    * Description: The purpose of this method is to add a new
    *              course in the application when the button
    *              is clicked.
    * Returns: Void.
    * --------------------------------------------------------*/
    public void addCourseBtnClick(View view)
    {
        /*Make a transition into the "AddCourse" activity window*/
        Bundle b = new Bundle();
        b.putSerializable("courseList", myCourses); //add the arraylist with all the current courses

        Intent intentAddCourse = new Intent(this,AddCourseActivity.class);
        intentAddCourse.putExtras(b);
        startActivity(intentAddCourse); //change of activity
    }


    /*----------------------------------------------------------
     * Function: processIntents()
     * Parameters: None
     * Description: The purpose of this function is to process the
     *              ArrayList that contains a List of Courses.
     * Returns: Void.
     * --------------------------------------------------------*/
    protected  void  processIntents()
    {
        try
        {
            Intent retrieveCourses = this.getIntent(); //get the intent

            /*Get the object from the intent*/
            Bundle getBundledObjects = retrieveCourses.getExtras();
            if((getBundledObjects != null) && (getBundledObjects.isEmpty() == false))
            {
                myCourses = (ArrayList<Course>) getBundledObjects.getSerializable("courseList"); //Get the list of current courses

                /*Now we need to extract the CourseNames from each Course object and .add() them
                * to an ArrayList<> of type String*/
                ArrayList<String> listOfCourses = new ArrayList<>();
                for (Course c : myCourses)
                {
                    listOfCourses.add(c.GetCourseName());
                }

                /*With our new ArrayList<String> that contains the courseNames, we will
                * populate the spinner*/
                populateSpinner(listOfCourses);
            }
        }
        catch (Exception e)
        {
            Log.d("MainActivity", "Something went wrong when retrieving Intent values on the MainActivity - " + e.toString());
        }
    }

    /*----------------------------------------------------------
     * Function: processIntents()
     * Parameters: ArrayList<String> newCourses
     * Description: The purpose of this method is to add the course
     *              names to the spinner.
     * Returns: Void.
     * --------------------------------------------------------*/
    protected void populateSpinner(ArrayList<String> newCourses)
    {
        try
        {
            /*Get the parameter*/
            List<String> spinnerArray =  newCourses;

            /*Assign the List<String> from above to this ArrayAdapter<String>*/
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            /*Assign the ArrayAdapter<String> to the Spinner so that it can display the course names*/
            Spinner sItems = (Spinner) findViewById(R.id.coursesSpinnerID);
            sItems.setAdapter(adapter);
            // point to the position null
            //sItems.setSelection(0);
        }
        catch (Exception e)
        {
            Log.d("MainActivity", "Something went wrong when populating the Spinner on the MainActivity - " + e.toString());
        }
    }

    /*----------------------------------------------------------
     * Function: transferToAddTasks()
     * Parameters: int indexOfCourse
     * Description: The purpose of this method is to is to transfer
     *              to another activity that will describe the
     *              contents of a course.
     * Returns: Void.
     * --------------------------------------------------------*/
    protected void transferToAddTasks(int indexOfCourse)
    {
        try
        {
            Bundle bundle = new Bundle();
            bundle.putSerializable("courseList", myCourses);

            /*Fill the Intent object and get outta here!*/
            Intent i = new Intent(this, VisualizeCourseActivity.class);
            i.putExtras(bundle); //The serialized Course() object will be assigned to the intent
            i.putExtra("courseToInspect", indexOfCourse);
            startActivity(i); //Go back to the Main Activity
        }
        catch (Exception e)
        {
            Log.d("MainActivity", "Something went wrong when selecting a course to add an assignment on the MainActivity - " + e.toString());
        }
    }



    public void mainThreadChangePicture()
    {
        try
        {




            //                ImageView imageView = (ImageView)((MainActivity)context).findViewById(R.id.image_viewID);
//                String url = "https://picsum.photos/200";
//          Picasso.with(context).load(url).into(imageView);



            Log.d("ThreadTest", "Success ");
            ImageView imageView = findViewById(R.id.image_viewID);
            String url = "https://loremflickr.com/200/200/bikini,girls,brazil/all";
            Picasso.with(this).load(url).into(imageView);

        }
        catch (Exception e)
        {
            Log.d("ThreadTest", "For this reason:" + e.toString());
        }


    }




}




