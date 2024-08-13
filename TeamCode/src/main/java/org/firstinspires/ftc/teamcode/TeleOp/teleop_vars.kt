package org.firstinspires.ftc.teamcode.TeleOp

import org.firstinspires.ftc.teamcode.Algorithms.chain_actioner

object teleop_vars {
    public var istransferred: Boolean = false
    public var isintaking: Boolean = false
    public var isspitting: Boolean = false
    public var isgoingback: Boolean = false
    public var isintakeposchanged: Boolean = false
    public var islidchanged: Boolean = false
    var islaunched: Boolean = false
    val transfersequence = chain_actioner()
}