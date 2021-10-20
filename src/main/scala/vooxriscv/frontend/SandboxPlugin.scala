package vooxriscv.frontend

import spinal.core._
import vooxriscv.utilities.Plugin

class SandboxPlugin extends Plugin{
  val setup = create early new Area{
    val frontend = framework.getService(classOf[FrontendPlugin])
    frontend.retain()
  }

  val logic = create late new Area{
    val stage = setup.frontend.pipeline.fetches.last
    import stage._
    out(stage.isValid)
    out(stage(Frontend.WORD))
    out(stage(Frontend.MASK))
    setup.frontend.release()
  }
}
