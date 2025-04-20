package com.anim.spectrumprogress

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Handler
import android.renderscript.Sampler.Value
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import kotlin.random.Random

class Spectrum : View {

    var scWidth = 0
    var scHeight = 0

    var mWidth:Int = 0;
    var mHeight:Int = 0;

    var halfHeight:Int = 0

    var sWidth:Int = 60

    var offset:Int = 10;

    var residual:Int=0

    var p:Paint?=null

    var anim1:ValueAnimator?=null

    var listener :ValueAnimator.AnimatorUpdateListener?=null

    var vheight:Array<Int>?=null

    var isProgress:Boolean = false

    var anims:Array<ValueAnimator>?=null

    var tArray:Array<Int>?=null

    var TOTALSWIDTH = 0

    var COUNT = 0

    var c:Context?=null

    var typedArray:TypedArray?=null

    var COLOR = 0
    var DURATION = 0

    var type:Type?=Type.VOICE
        get() = field
        set(value) {

            field = value
            invalidate()

        }





    constructor(c:Context) : super(c) {

        this.c = c;


    }

    constructor(c:Context , a:AttributeSet) : super(c,a) {

        this.c = c;

        typedArray = c.theme.obtainStyledAttributes(a,R.styleable.Spectrum,0,0)

        init(c)


    }

    constructor(c:Context , a:AttributeSet , defStyle:Int) : super(c,a,defStyle) {

        this.c = c;

        typedArray = c.theme.obtainStyledAttributes(a,R.styleable.Spectrum,0,0)

        init(c)

    }

    private fun init(c:Context) {

        COLOR = typedArray!!.getColor(R.styleable.Spectrum_spectrumColor
        ,c.resources.getColor(R.color.black))

        sWidth = Utils.getPxFromDp(c
            ,typedArray!!.getInt(R.styleable.Spectrum_spectrumWidth,60))

        offset = Utils.getPxFromDp(c
            ,typedArray!!.getInt(R.styleable.Spectrum_spectrumOffset,10))

        DURATION = typedArray!!.getInt(R.styleable.Spectrum_duration,1000)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (isProgress) {

            for (i in 0 until anims!!.size) {

                if (type == Type.MEDIA) {

                    val dist = (sWidth+offset)*i


                    canvas.drawRect(dist.toFloat()
                        ,vheight!![i].toFloat()
                        ,(dist+sWidth).toFloat()
                        ,height.toFloat(),p!!)


                } else {

                    var bottom = halfHeight-vheight!![i]

                    val dist = (sWidth+offset)*i

                    canvas.drawRoundRect(dist.toFloat()
                        ,vheight!![i].toFloat()
                        ,(dist+sWidth).toFloat()
                        ,(halfHeight+bottom).toFloat(),20f,20f,p!!)

                }

            }

        } else {

            if (type == Type.MEDIA) {

                canvas.translate(0f,height.toFloat())

            } else {

                canvas.translate(0f,height/2.toFloat())


            }

        }

    }

    inner class MyAnimator : ValueAnimator() {

        override fun addUpdateListener(listener: AnimatorUpdateListener?) {
            super.addUpdateListener(listener)
        }

    }

    fun startAnim(t: Type) {

        type = t

        Handler().post({

            p = Paint()
            p!!.isAntiAlias = true
            p!!.color = COLOR

            scWidth = c!!.resources.displayMetrics.widthPixels
            scHeight = c!!.resources.displayMetrics.heightPixels

            mWidth = width;
            mHeight = height

            halfHeight = mHeight/2

            if (type == Type.MEDIA) {

                offset = 0;
            }

            TOTALSWIDTH = sWidth+offset

            COUNT = scWidth/TOTALSWIDTH

            vheight = Array(COUNT){halfHeight}

            if (type == Type.VOICE) {


                anims = Array(COUNT){i->

                    ValueAnimator().apply {

                        setIntValues(halfHeight,0,halfHeight)
                        setDuration(DURATION.toLong())
                        interpolator = LinearInterpolator()
                        repeatCount = ObjectAnimator.INFINITE

                        startDelay = ((i+1)*100).toLong()
                        addUpdateListener {

                            isProgress = true

                            vheight!![i] = it.getAnimatedValue() as Int

                            invalidate()

                        }


                    }

                }






            } else if (type == Type.PROGRESS) {

                tArray = Array(COUNT) {i->

                    Random.nextInt(COUNT)*300
                }

                anims = Array(COUNT){i->

                    ValueAnimator().apply {

                        setIntValues(halfHeight,0,halfHeight)
                        setDuration(DURATION.toLong())
                        interpolator = LinearInterpolator()
                        repeatCount = ObjectAnimator.INFINITE

                        startDelay = tArray!![i].toLong()
                        addUpdateListener {

                            isProgress = true

                            vheight!![i] = it.getAnimatedValue() as Int

                            invalidate()

                        }


                    }

                }

            } else if (type == Type.MEDIA) {

                anims = Array(COUNT){i->

                    ValueAnimator().apply {

                        setIntValues(height,0,height)
                        setDuration(DURATION.toLong())
                        interpolator = LinearInterpolator()
                        repeatCount = ObjectAnimator.INFINITE

                        startDelay = ((i+1)*100).toLong()
                        addUpdateListener {

                            isProgress = true

                            vheight!![i] = it.getAnimatedValue() as Int

                            invalidate()

                        }


                    }

                }



            }

            for (i in 0 until anims!!.size) {

                isRunning = true

                anims!![i].start()

            }


        })

    }

    var isRunning:Boolean=false

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()


    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()




    }

    fun resumeAnims() {

        if (isRunning) {

            for (i in 0 until anims!!.size) {

                anims!![i].resume()

            }


        }
    }

    fun pauseAnims() {

        if (isRunning) {

            for (i in 0 until anims!!.size) {

                anims!![i].pause()

            }


        }

    }
}