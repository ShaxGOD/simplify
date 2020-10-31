package com.example.simplify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TopMovies extends AppCompatActivity {
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_movies);
        listView = findViewById(R.id.list);
        List<SetMovie> setMovie;
        setMovie = new ArrayList<>();
        setMovie.add(new SetMovie("CRIP CAMP: A DISABILITY REVOLUTION","https://themighty.com/wp-content/uploads/2020/03/crip-camp-1007x1024-1280x640.jpg","1",
                "As entertaining as it is inspiring, Crip Camp uses one group\\'s\n" +
                "        emarkable story to highlight hope for the future and the power of community","Nicole Newnham, Jim LeBrecht"));
        setMovie.add(new SetMovie("DRIVEWAYS","https://i.ytimg.com/vi/0-j1p-U7nKw/hqdefault.jpg","2",
                "Understated yet powerful, Driveways is a character study anchored in fundamental decency -- and a poignant farewell to Brian Dennehy.","Andrew Ahn"));
        setMovie.add(new SetMovie("MUCHO MUCHO AMOR: THE LEGEND OF WALTER MERCADO","https://www.letsott.com/assets/uploads/posts/MUCHO-MUCHOAMOR-H1-min.jpg",
                "3","An absorbing and affectionate tribute to a unique individual, Mucho Mucho Amor should prove fascinating for Walter Mercado fans as well as first-timers."," Kareem Tabsch , Cristina Costantini"));
       setMovie.add(new SetMovie("WELCOME TO CHECHNYA","https://i.obozrevatel.com/news/2020/6/2/welcome-to-chechnya-ka-final.jpg?size=900x600","4",
               "An illuminating and urgent call to action, Welcome to Chechnya portrays the horrors of the mass persecution of the LGBTQ+ community in the Chechen Republic with tenacity and tenderness.","David France"));
        setMovie.add(new SetMovie("DICK JOHNSON IS DEAD","https://whatsupnewp.com/wp-content/uploads/2020/10/Dick-Johnson-Is-Dead-A.jpg","5",
                "Dick Johnson Is Dead celebrates a life with bittersweet humor and grace, offering a deeply resonant perspective on mortality in the bargain.","Kirsten Johnson"));
        setMovie.add(new SetMovie("ATHLETE A","https://inreview52838412.files.wordpress.com/2020/06/15930183090263.jpg","6","Harrowing yet essential viewing, Athlete A shines an unforgiving light on horrific abuses -- as well as the culture that allowed them to continue unabated for years.","Bonni Cohen, Jon Shenk"));
        setMovie.add(new SetMovie("ALL IN: THE FIGHT FOR DEMOCRACY","https://cdn.flickeringmyth.com/wp-content/uploads/2020/09/All-In-The-Fight-for-Democracy.jpg","7","All In: The Fight for Democracy lives up to its title as a galvanizing rallying cry for voters to exercise -- and preserve -- their right to be heard.",
                "Lisa Cortés, Liz Garbus"));
        setMovie.add(new SetMovie("A SECRET LOVE","https://socialitelife.com/wp-content/uploads/2020/04/a-secret-love.jpg","8","In telling one couple's story, A Secret Love pays understated yet powerful tribute to a lifetime of choices and sacrifices made in the name of enduring devotion.","Chris Bolan"));
        setMovie.add(new SetMovie("REWIND","https://static.rogerebert.com/uploads/blog_post/primary_image/festivals/tribeca-2019-rewind/Rewind-Documentary.jpg","9",
                "Rewind pulls at the roots of a family's horrific trauma with a deeply personal documentary that's hard to watch, but worth the effort.","Sasha Joseph Neulinger"));
        setMovie.add(new SetMovie("HOUSE OF HUMMINGBIRD (BEOLSAE)","https://pmcvariety.files.wordpress.com/2019/04/house-of-hummingbird.jpg","10",
                "A striking debut for writer-director Kim Bora, House of Hummingbird delicately captures a turning point in one young woman's life.","Kim Bora"));
        CustomAdapter customAdapter = new CustomAdapter(this,R.layout.list_items,setMovie);
        listView.setAdapter(customAdapter);
    }
}