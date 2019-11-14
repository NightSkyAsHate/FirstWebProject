<template>
    <v-container>
        <v-row align="center">
            <v-col lg="2">
                <v-text-field
                        name="cargo"
                        label="Value"
                        v-model="cargo_id">
                </v-text-field>
            </v-col>
            <v-col lg="2">
                <v-btn color="light-blue" dark @click="find">
                    <v-icon dark left>search</v-icon>
                </v-btn>
            </v-col>
        </v-row>
        <v-row>
            <v-container>
                <div class="text--primary">
                    Cargos
                </div>
                <v-row dense>
                    <v-col cols="6" sm="4">
                        <v-container fluid>
                            <v-card class="mx-auto" v-for="cargo in this.cargoCurrentUser" :cargo="cargo" :key="cargo.id">
                                <v-container>
                                    <v-row>
                                        <v-col class="d-flex" cols="12" sm="6">
                                            <cargo-element :value="cargo.id" label="Id"></cargo-element>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col class="d-flex" cols="12" sm="6">
                                            <cargo-element :value="cargo.amount" label="Amount"></cargo-element>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col class="d-flex" cols="12" sm="6">
                                            <cargo-element :value="cargo.weight_value" label="Weight"></cargo-element>
                                            <weight-form :weight="cargo.weight" label-text="Weight type"></weight-form>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col class="d-flex" cols="12" sm="6">
                                            <cargo-element :value="cargo.length" label="Length"></cargo-element>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col class="d-flex" cols="12" sm="6">
                                            <cargo-element :value="cargo.width" label="Width"></cargo-element>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col class="d-flex" cols="12" sm="6">
                                            <cargo-element :value="cargo.height" label="Height"></cargo-element>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col class="d-flex" cols="12" sm="6">
                                            <v-chip class="ma-2" v-for="condition in cargo.special_conditions">
                                                {{condition.name}}
                                            </v-chip>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col class="d-flex" cols="12" sm="6">
                                            <user-form :user="cargo.user"></user-form>
                                        </v-col>
                                    </v-row>
                                </v-container>
                            </v-card>
                        </v-container>
                    </v-col>
                </v-row>
            </v-container>
        </v-row>
    </v-container>
</template>

<script>
    import { mapState } from 'vuex'
    import { mapActions } from 'vuex'
    import CargoElement from "components/cargo/cargoElement.vue";
    import WeightForm from "components/cargo/weightForm.vue";
    import UserForm from "components/cargo/userForm.vue";
    export default {
        name: "CargoSearch",
        components: {CargoElement, UserForm, WeightForm},
        computed: mapState(['cargoCurrentUser', 'profile']),
        data(){
            return{
                cargo_id: '',
            }
        },
        methods:{
            ...mapActions(['getUsersCargos', 'getCargoById']),
            find(){
                if (this.cargo_id == ''){
                    this.getUsersCargos()
                } else {
                    this.getCargoById(this.cargo_id)
                }
            }
        },
        created() {
            this.getUsersCargos()
        }
    }
</script>

<style scoped>

</style>