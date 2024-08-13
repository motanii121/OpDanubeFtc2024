package org.firstinspires.ftc.teamcode.Systems

class NanoClock {

    var lt = System.nanoTime()

    fun seconds(): Double {
        return (System.nanoTime() - lt) / 1e9
    }

    fun reset() {
        lt = System.nanoTime()
    }
}