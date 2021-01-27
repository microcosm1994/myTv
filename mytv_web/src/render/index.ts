import {remote, BrowserWindow} from 'electron'

// 设置窗口大小
export const setSize = (w: number, h: number) => {
    remote.getCurrentWindow().setSize(w, h)
}

// 关闭当前窗口
export const close = () => {
    remote.getCurrentWindow().close()
}
