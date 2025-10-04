import { defineConfig, loadEnv } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig(({mode}) => {
  const env = loadEnv(mode, process.cwd(), '')

  return {
  plugins: [react()],
  server: {
    open: true,
    port: 3000,
    proxy: {
      '/api': {
        target: `http://${env.VITE_API_URL}:8080`,
        changeOrigin: true,
      },
      '/v1': {
        target: 'http://api.marketstack.com',
        changeOrigin: true,
      },
    },
  },}
})
