package Day2

sealed trait State {
}

case object Running extends State
case object Halted extends State
case object Stopped extends State
