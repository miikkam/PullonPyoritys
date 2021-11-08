package my.application.pullonpyoritys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView pullo;
    private Random random = new Random();
    private int lastDir;
    private boolean spn;
    Button button;
    View screenView;
    int[] taustat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullo =findViewById(R.id.pullo);

        taustat = new int[]{R.drawable.kivilattia, R.drawable.laattalattia,
                           R.drawable.pikist, R.drawable.kendo};
        button = findViewById(R.id.btn);
        screenView = findViewById(R.id.main_layout);

        // onClick funktio taustakuvan vaihdolle
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int array_length = taustat.length;

                Random rand = new Random();

                int rand_number = rand.nextInt(array_length);

                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                                          taustat[rand_number]));
            }
        });
    }
        //onClick funktio pullonpyöritykselle
    public void spinBottle(View v) {
        if (!spn) {
            int num = random.nextInt(1800);

            //akseli keskellä pulloa
            float pivotX = pullo.getWidth() / 2;
            float pivotY = pullo.getHeight() / 2;

            Animation rotate = new RotateAnimation(lastDir, num, pivotX, pivotY);

            //pyörii 2,5 sekuntia
            rotate.setDuration(2500);

            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spn = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spn = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });

            //tallentaa pullon asennon
            lastDir = num;

            pullo.startAnimation(rotate);
        }
    }
}