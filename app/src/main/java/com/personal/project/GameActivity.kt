package com.personal.project
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.math.floor

class GameActivity : AppCompatActivity() {
    private lateinit var scoreText: TextView
    private lateinit var healthText: TextView
    private lateinit var fuelText: TextView
    private lateinit var rocket : ImageView
    private lateinit var fuel : ImageView
    private lateinit var star : ImageView
    private lateinit var alien : ImageView
    private lateinit var fireball : ImageView
    private lateinit var sun : ImageView
    private var height : Int = 0
    private var width : Int = 0
    private var score : Int = 0
    private var health : Int = 3
    private var fuelstatus : Int = 5000
    private var timer = Timer()
    private var handler = Handler(Looper.getMainLooper())
    private var fuelX:Float = 0.0F
    private var fuelY:Float = 0.0F
    private var starX:Float = 0.0F
    private var starY:Float = 0.0F
    private var alienX:Float = 0.0F
    private var alienY:Float = 0.0F
    private var fireballX:Float = 0.0F
    private var fireballY:Float = 0.0F
    private var sunX:Float = 0.0F
    private var sunY:Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        scoreText = findViewById(R.id.textView3)
        healthText = findViewById(R.id.textView6)
        fuelText = findViewById(R.id.textView8)
        rocket = findViewById(R.id.imageView2)
        height = Resources.getSystem().displayMetrics.heightPixels
        width = Resources.getSystem().displayMetrics.widthPixels
        fuel = findViewById(R.id.imageView3)
        star = findViewById(R.id.imageView_Star)
        alien = findViewById(R.id.imageView_Alien)
        fireball = findViewById(R.id.imageView_Fireball)
        sun = findViewById(R.id.imageView_Sun)
        scoreText.text = getString(R.string.score)
        healthText.text = getString(R.string.health)
        fuelText.text = getString(R.string.fuel)
        timer.schedule(object:TimerTask() {
            override fun run() {
                handler.post { updatePosition() }
            }
        }, 0, 20)
    }
    fun updatePosition() {
        fuelstatus -= 1
        fuelText.text = fuelstatus.toString()
        if (fuelstatus <= 0 || health <= 0) {
            timer.cancel()
            handler.removeCallbacksAndMessages(null);
            startActivity(Intent(this, GameFinishedActivity::class.java))
        }
        checkCollision()
        if (fuelY >= height.toFloat() - 500)
        {
            fuelY = (0 - height).toFloat()
            fuelX = floor(Math.random() * (width - fuel.width)).toFloat()
        } else {
            fuelY = (fuelY + 10.0F)
        }
        if (starY >= height.toFloat() - 500)
        {
            starY = (0 - height).toFloat()
            starX = floor(Math.random() * (width - star.width)).toFloat()
        } else {
            starY = (starY + 13.0F)
        }
        if (alienY >= height.toFloat() - 500)
        {
            alienY = (0 - height).toFloat()
            alienX = floor(Math.random() * (width - alien.width)).toFloat()
        } else {
            alienY = (alienY + 25.0F)
        }
        if (fireballY >= height.toFloat() - 500)
        {
            fireballY = (0 - height).toFloat()
            fireballX = floor(Math.random() * (width - fireball.width)).toFloat()
        } else {
            fireballY = (fireballY + 35.0F)
        }
        if (sunY >= height.toFloat() - 500)
        {
            sunY = (0 - height).toFloat()
            sunX = floor(Math.random() * (width - sun.width)).toFloat()
        } else {
            sunY = (sunY + 24.0F)
        }
        fuel.x = fuelX
        fuel.y = fuelY
        star.x = starX
        star.y = starY
        alien.x = alienX
        alien.y = alienY
        fireball.x = fireballX
        fireball.y = fireballY
        sun.x = sunX
        sun.y = sunY
    }
    private fun checkCollision() {
        if ((fuelY in (height.toFloat() - 600)..(height.toFloat() - 500) &&
                    rocket.x <= fuelX && fuelX <= rocket.x + 150))
        {
            fuelY = height.toFloat()
            fuel.y = fuelY
            fuelstatus += 500
            fuelText.text = fuelstatus.toString()
        }
        if ((star.y in (height.toFloat() - 600)..(height.toFloat() - 500) &&
                    rocket.x <= starX && starX <= rocket.x + 150))
        {
            starY = height.toFloat()
            star.y = starY
            score += 5
            scoreText.text = score.toString()
        }
        if ((sunY in (height.toFloat() - 600)..(height.toFloat() - 500) &&
                    rocket.x <= sunX && sunX <= rocket.x + 150))
        {
            sunY = height.toFloat()
            sun.y = sunY
            score += 10
            scoreText.text = score.toString()
        }
        if ((alienY in (height.toFloat() - 600)..(height.toFloat() - 500) &&
                    rocket.x <= alienX && alienX <= rocket.x + 150))
        {
            alienY = height.toFloat()
            alien.y = alienY
            health -= 1
            healthText.text = health.toString()
        }
        if ((fireballY in (height.toFloat() - 600)..(height.toFloat() - 500) &&
                    rocket.x <= fireballX && fireballX <= rocket.x + 150))
        {
            fireballY = height.toFloat()
            fireball.y = fireballY
            health -= 1
            healthText.text = health.toString()
        }

    }
    override fun onTouchEvent (event:MotionEvent):Boolean {
        rocket.x = event.x - 150
        return super.onTouchEvent(event)
    }
}