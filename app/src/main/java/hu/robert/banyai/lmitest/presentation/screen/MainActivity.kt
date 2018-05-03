package hu.robert.banyai.lmitest.presentation.screen

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import lmitest.banyai.robert.com.logmeintest.R
import hu.robert.banyai.lmitest.presentation.common.SpaceItemDecoration
import hu.robert.banyai.lmitest.presentation.common.extension.clear
import hu.robert.banyai.lmitest.presentation.common.extension.setVisibility
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    @Inject
    lateinit var mainAdapter: MainAdapter

    @Inject
    lateinit var spaceItemDecoration: SpaceItemDecoration

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var uiDisposable: CompositeDisposable

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = mainAdapter
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(spaceItemDecoration)

        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel::class.java)

        mainViewModel.uiStateObserver.observe(this, Observer {
            when (it) {
                is MainViewState.DataUpdateState -> {
                    mainAdapter.update(it.mainUiModel.data)
                    recyclerView.smoothScrollToPosition(mainAdapter.itemCount.dec())

                }
                is MainViewState.ErrorState -> {
                    Toast.makeText(this, it.throwable.message, Toast.LENGTH_LONG).show()
                }
                is MainViewState.DisconnectState -> {
                    containerOffline.setVisibility(true)
                }
                is MainViewState.ConnectState -> {
                    containerOffline.setVisibility(false)
                }
            }
        })

        uiDisposable.add(
                RxView.clicks(imgSend)
                        .subscribe {
                            mainViewModel.sendMessage(edtMessage.text.toString())
                            edtMessage.clear()
                        }
        )

        uiDisposable.add(
                RxTextView.textChanges(edtMessage)
                        .subscribe {
                            imgSend.setVisibility(it.isNotEmpty())
                        }
        )

        uiDisposable.add(
                RxView.clicks(tvReconnect)
                        .subscribe {
                            mainViewModel.reconnect()
                        }
        )
    }

    override fun onDestroy() {
        uiDisposable.clear()
        super.onDestroy()
    }
}
