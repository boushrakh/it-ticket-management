import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'node:path'
import autoprefixer from 'autoprefixer'

// https://vite.dev/config/
export default defineConfig({
  base: './',
  plugins: [react()],
  resolve: {
    alias: [
      {
        find: /^src\/(.*)$/,
        replacement: `${path.resolve(__dirname, 'src')}/$1`,
      },
      {
        find: /^@\/(.*)$/,
        replacement: `${path.resolve(__dirname, 'src')}/$1`,
      },
      {
        find: 'src',
        replacement: path.resolve(__dirname, 'src'),
      },
    ],
    extensions: ['.mjs', '.js', '.ts', '.jsx', '.tsx', '.json', '.scss'],
  },
  css: {
    postcss: {
      plugins: [autoprefixer({})],
    },
  },
  build: {
    outDir: 'build',
  },
  server: {
    port: 3000,
    proxy: {},
  },
})
