// сочетание определения класса и конструктора одновременно объявляет переменные и задаёт их значения
class Rect(var x: Int, var y: Int, val _width: Int, val _height: Int) : Movable, Figure(0), Transforming {
    var width = _width
    init { if (_width < 0) throw Exception() }

    var height = _height
    init { if (_height < 0) throw Exception() }

    // TODO: реализовать интерфейс Transforming
    var color: Int = -1 // при объявлении каждое поле нужно инициализировать

    lateinit var name: String // значение на момент определения неизвестно (только для объектных типов)
    // дополнительный конструктор вызывает основной
    constructor(rect: Rect) : this(rect.x, rect.y, rect.width, rect.height)

    // нужно явно указывать, что вы переопределяете метод
    override fun move(dx: Int, dy: Int) {
        x += dx; y += dy
    }

    // для каждого класса area() определяется по-своему
    override fun area(): Float {
        return (width*height).toFloat() // требуется явное приведение к вещественному числу
    }

    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        if (centerX == x && centerY == y) return
        val (signX, signY) = if (direction == RotateDirection.CounterClockwise) { Pair(-1, 1) } else { Pair(1, -1) }

        x = (signX * (y - centerY) + centerX).also {
            y = signY * (x - centerX) + centerY
        }

        width = height.also { height = width }
    }

    override fun resize(zoom: Int) {
        width *= zoom
        height *= zoom
    }

    override fun toString(): String {
        return "Rectangle { x: $x, y: $y, width: $width, height: $height }"
    }
}