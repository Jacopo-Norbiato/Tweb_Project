
import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'


// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  build: {
    rollupOptions: {
      external: [
          'node:router',
          'node:path',
          'node:https'
      ]
    },
    compilerOptions: {
      "assets": [
        {
          "include" :"../assets",
          "outDir": "dist/public",
          "watchAssets":true,
        },
        {
          "include" :"../views",
          "outDir": "dist/views",
          "watchAssets":true,
        },
      ],
      "watchAssets": true,
    }
  },
  base: "/DemoJac1",
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
})