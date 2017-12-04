package com.vetkoli.sanket.standapp.communication

import com.squareup.otto.Bus
import com.squareup.otto.ThreadEnforcer

/**
 * Created by Sanket on 4/12/17.
 */

object BusProvider {

    val instance = Bus(ThreadEnforcer.ANY)

}
