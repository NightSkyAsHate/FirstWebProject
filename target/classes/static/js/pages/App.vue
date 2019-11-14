<template>
    <v-app>
        <v-app-bar app color="blue">
            <v-btn text large class="ma-1" color="black"  href="/">
                Delivery
            </v-btn>
            <v-btn text class="ma-9" color="black" @click="showAboutUs">
                About Us
            </v-btn>
            <v-btn text class="ma-9" color="black" @click="showReviews">
                Reviews
            </v-btn>
            <v-btn text class="ma-9" color="black" @click="showMyCargos" v-if="profile">
                Cargos
            </v-btn>
            <v-btn text class="ma-9" color="black" @click="showAddForm" v-if="isManager">
                Add cargo
            </v-btn>
            <v-spacer></v-spacer>
            <sign-in-form v-if="!profile"></sign-in-form>
            <div v-else>
                <v-container>
                    <v-row align="center">
                        Hi, {{ profile.username }}
                        <v-btn color="black" dark icon href="/logout">
                            <v-icon>exit_to_app</v-icon>
                        </v-btn>
                    </v-row>
                </v-container>
            </div>
        </v-app-bar>
        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
    import SignInForm from "components/user/SignInForm.vue";
    import {mapState} from 'vuex'
    import Main_page from "components/view/main_page.vue";
    export default {
        name: "App",
        components: {
            Main_page,
            SignInForm,
        },
        data(){
            return{
                isManager: false
            }
        },
        computed: mapState(['profile']),
        methods: {
            showAboutUs(){
                this.$router.push('/about')
            },
            showReviews(){
                this.$router.push('/reviews')
            },
            showMyCargos() {
                if (this.profile.roles.indexOf('MANAGER') != -1){
                    this.$router.push('/cargos_manager')
                } else {
                    this.$router.push('/cargos')
                }
            },
            showAddForm(){
                this.$router.push('/addForm')
            }
        },
        beforeMount() {
            if (this.$route.path === '/login'){
                this.$route.replace('/?needLogin=1')
            }
        },
        created() {
            if (this.profile) {
                if (this.profile.roles.indexOf('MANAGER') != -1) {
                    this.isManager = true
                } else {
                    this.isManager = false
                }
            }
        }
    }
</script>

<style scoped>
</style>