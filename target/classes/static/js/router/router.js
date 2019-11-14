import Vue from 'vue'
import VueRouter from "vue-router"
import AboutUs from "pages/AboutUs.vue"
import main_page from "components/view/main_page.vue"
import reviews from "pages/reviewPage.vue"
import cargos from "pages/cargos.vue"
import cargo_managment from "components/cargo/cargo_managment.vue";
import addForm from "components/cargo/addForm.vue"

Vue.use(VueRouter)

const routes = [
    {path: '/about', component: AboutUs},
    {path: '/', component: main_page},
    {path: '/reviews', component: reviews},
    {path: '/cargos', component: cargos},
    {path: '/cargos_manager', component: cargo_managment},
    {path: '/addForm', component: addForm},
]

export default new VueRouter({
    mode: 'history',
    routes
})