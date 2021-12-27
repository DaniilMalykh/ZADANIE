fun main() {

    // интерфейсы удобно использовать в коллекциях
    val figures: Array<Movable>
    val movable: Movable = Rect(0,0,1,1)
    movable.move(1,1)
    // переменной базового класса
    val f: Figure = Rect(0,0,1,1)
    val f2: Figure = Circle(5, 0, 0)

    println("$f - area: ${f.area()}")
    println("$f2 - area: ${f2.area()}")

    val tr: Transforming = Rect(0,3,1,2)
    val tc: Transforming = Circle(3, 0, 0)

    println("Rectangle before resize(2): $tr")
    tr.resize(2)
    println("Rectangle after resize(2): $tr")

    println("Rectangle before rotate(RotateDirection.Clockwise, 0, 0): $tr")
    tr.rotate(RotateDirection.Clockwise, 0, 0)
    println("Rectangle after rotate(RotateDirection.Clockwise, 0, 0): $tr")

    println("Circle before resize(2): $tc")
    tc.resize(2)
    println("Circle after resize(2): $tc")

    println("Circle before rotate(RotateDirection.CounterClockwise, 0, 3): $tc")
    tc.rotate(RotateDirection.CounterClockwise, 0, 3)
    println("Circle after rotate(RotateDirection.CounterClockwise, 0, 3): $tc")



}