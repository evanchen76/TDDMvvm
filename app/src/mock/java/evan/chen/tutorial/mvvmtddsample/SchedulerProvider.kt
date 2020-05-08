package evan.chen.tutorial.mvvmtddsample

import io.reactivex.schedulers.Schedulers

class SchedulerProvider  {
     companion object {
          fun computation() = Schedulers.trampoline()
          fun mainThread() = Schedulers.trampoline()
          fun io() = Schedulers.trampoline()
     }
}