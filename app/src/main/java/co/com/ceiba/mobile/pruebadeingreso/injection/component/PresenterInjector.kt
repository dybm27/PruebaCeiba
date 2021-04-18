package co.com.ceiba.mobile.pruebadeingreso.injection.component

import co.com.ceiba.mobile.pruebadeingreso.injection.module.ContextModule
import co.com.ceiba.mobile.pruebadeingreso.injection.module.RetrofitModule
import co.com.ceiba.mobile.pruebadeingreso.injection.module.RoomModule
import co.com.ceiba.mobile.pruebadeingreso.presenter.post.PostPresenter
import co.com.ceiba.mobile.pruebadeingreso.presenter.user.UserPresenter
import co.com.ceiba.mobile.pruebadeingreso.view.BaseView
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ContextModule::class), (RetrofitModule::class), (RoomModule::class)])
interface PresenterInjector {

    fun inject(postPresenter: PostPresenter)
    fun inject(userPresenter: UserPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun retrofitModule(retrofitModule: RetrofitModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder

        fun roomModule(roomModule: RoomModule): Builder
    }
}