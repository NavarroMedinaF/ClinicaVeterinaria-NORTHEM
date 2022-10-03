/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
  "./*.html",
  "./servicisos/*.html",
  "./assets/css/*.css",
  './src/**/*.{html,js}', 
  './assets/node_modules/preline/dist/*.js',
  './node_modules/tw-elements/dist/js/**/*.js'

  ],
  theme: {
    screens: {
      'tablet': '640px',
      // => @media (min-width: 640px) { ... }

      'laptop': '1024px',
      // => @media (min-width: 1024px) { ... }

      'desktop': '1280px',
      // => @media (min-width: 1280px) { ... }
    },
    extend: {},
  },
  plugins: [
    require('./assets/node_modules/preline/plugin.js'),
]
}
