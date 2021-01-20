import { remote } from 'electron'

// 设置窗口大小
export const setSize = (w: number, h: number) => {
  remote.getCurrentWindow().setSize(w, h)
}
