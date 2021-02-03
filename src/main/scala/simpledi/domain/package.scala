package simpledi

package object domain {
    case class Error(msg: String)
    type Result[T] = Either[Error, T]
}