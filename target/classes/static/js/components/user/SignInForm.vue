<template>
    <v-dialog v-model="dialog" width="600" @click:outside="click">
        <template v-slot:activator="{ on }">
            <v-btn class="ma-2" color="light-blue" dark small v-on="on" right>
                <v-icon dark left>person</v-icon>
                Sign In & Sign Up
            </v-btn>
        </template>
        <v-card>
            <v-tabs v-model="tab" background-color="transparent">
                <v-tab>Sign In</v-tab>
                <v-tab>Sign up</v-tab>
            </v-tabs>
            <v-tabs-items v-model="tab">
                <v-tab-item>
                    <v-card>
                        <v-card-text>
                            <form action="/login" method="post">
                                <v-col>
                                    <v-text-field
                                            name="username"
                                            label="Username"
                                            v-model="username"
                                            required
                                    ></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field
                                            name="password"
                                            label="Password"
                                            type="password"
                                            v-model="password"
                                            required
                                    ></v-text-field>
                                </v-col>
                                <div class="text-center">
                                    <v-btn color="light-blue" dark type="submit">Enter</v-btn>
                                    <v-alert
                                            class="mt-5"
                                            :value="alert"
                                            :color="alertColor"
                                            dark
                                            border="top"
                                            transition="scale-transition"
                                    >
                                        {{alertMessage}}
                                    </v-alert>
                                </div>
                            </form>
                        </v-card-text>
                    </v-card>
                </v-tab-item>
                <v-tab-item>
                    <v-card flat>
                        <v-card-text>
                            <v-container>
                                <v-col cols="12">
                                    <v-text-field
                                            label="Username*"
                                            required
                                            v-model="username"
                                            :error-messages="UsernameErrors"
                                            @input="$v.username.$touch()"
                                            @blur="$v.username.$touch()"
                                    ></v-text-field>
                                </v-col>
                                <v-col cols="12">
                                    <v-text-field
                                            label="Password*"
                                            type="password"
                                            required
                                            v-model="password"
                                            :error-messages="passwordErrors"
                                            @input="$v.password.$touch()"
                                            @blur="$v.password.$touch()"
                                    ></v-text-field>
                                </v-col>
                                <v-col cols="12">
                                    <v-text-field label="Phone number*"
                                                  required
                                                  v-model="phoneNumber"
                                                  :error-messages="phoneErrors"
                                                  @input="$v.phoneNumber.$touch()"
                                                  @blur="$v.phoneNumber.$touch()"
                                    ></v-text-field>
                                </v-col>
                                <div class="text-center">
                                    <v-btn color="light-blue" dark @click="register">SighUp</v-btn>
                                    <v-alert
                                            class="mt-5"
                                            :value="alert"
                                            :color="alertColor"
                                            dark
                                            border="top"
                                            transition="scale-transition"
                                    >
                                        {{alertMessage}}
                                    </v-alert>
                                </div>
                            </v-container>
                        </v-card-text>
                    </v-card>
                </v-tab-item>
            </v-tabs-items>
        </v-card>
    </v-dialog>
</template>

<script>
    import {validationMixin} from 'vuelidate'
    import {required, numeric} from 'vuelidate/lib/validators'
    import {mapState} from 'vuex'
    import { mapActions } from 'vuex'
    import usersApi from "api/user"

    export default {
        name: "SignInForm",
        mixins: [validationMixin],
        validations: {
            username: {required},
            phoneNumber: {required, numeric},
            password: {required}
        },
        computed: mapState(['profile']),
        data() {
            return {
                tab: null,
                dialog: false,
                alert: false,
                username: '',
                password: '',
                phoneNumber: '',
                alertMessage: '',
                alertColor: '',
            }
        },
        methods: {
            ...mapActions(['updateUserInfo']),
            register(){
                this.$v.$touch()
                if (this.$v.$invalid){

                } else {
                    const user = {
                        username: this.username,
                        password: this.password,
                        phoneNumber: this.phoneNumber,
                        roles: ["USER"]
                    };

                    usersApi.save(user).then(result => {
                        if (result.data === false){
                            this.alertMessage = "User with such username already exists"
                            this.alertColor = 'red'
                        } else {
                            this.alertMessage = "Successful"
                            this.alertColor = 'green'
                        }
                    })

                    this.alert = true;
                }
            },
            click() {
                this.$v.$reset()
                this.alert = false
                this.dialog = false
                this.username = ''
                this.password = ''
                this.phoneNumber = ''
            }
        },
        computed: {
            UsernameErrors(){
                const  errors = []
                if (!this.$v.username.$dirty) return errors
                !this.$v.username.required && errors.push('Enter username')
                return errors
            },
            phoneErrors() {
                const errors = []
                if (!this.$v.phoneNumber.$dirty) return errors
                !this.$v.phoneNumber.required && errors.push('Enter phone number')
                !this.$v.phoneNumber.numeric && errors.push('Phone number must consist only digits')
                return errors
            },
            passwordErrors() {
                const errors = []
                if (!this.$v.password.$dirty) return errors
                !this.$v.password.required && errors.push('Enter password')
                return errors
            }
        },
        mounted() {
            if (this.$route.query.needLogin === '1'){
                this.dialog = true
            }
        }
    }
</script>

<style scoped>

</style>