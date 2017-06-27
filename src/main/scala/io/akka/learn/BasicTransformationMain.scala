package io.akka.learn

/**
  * Created by animesh on 6/27/17.
  * Created by  on 6/27/17.
  */
object BasicTransformationMain xtends App {

  implicit val system = ActorSystem("Sys")
  import system.dispatcher
  implicit val materializer = ActorMaterializer()

  val text =
  """|Lorem Ipsum is simply dummy text of the printing and typesetting industry.
     |Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
     |when an unknown printer took a galley of type and scrambled it to make a type
     |specimen book.""".stripMargin

  Source(() => text.split("\\s").iterator).
  map(_.toUpperCase).
  runForeach(println).
  onComplete(_ => println("Done"))
}
