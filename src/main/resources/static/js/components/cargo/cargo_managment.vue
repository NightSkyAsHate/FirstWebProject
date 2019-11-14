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
                <v-select
                        :items="items"
                        label="Type of search"
                        dense
                        v-model="choice">
                </v-select>
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
                            <v-card class="mx-auto" v-for="cargo in this.cargosAll" :cargo="cargo" :key="cargo.id">
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
                                    <v-row justify="space-around">
                                        <v-col class="d-flex" cols="12" sm="6">
                                            <!--                                        <v-btn color="black" dark icon>-->
                                            <!--                                            <v-icon>edit</v-icon>-->
                                            <!--                                        </v-btn>-->
                                            <v-btn color="black" dark icon @click="deleteCargo(cargo)">
                                                <v-icon>delete</v-icon>
                                            </v-btn>
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
    import {mapState} from 'vuex'
    import { mapActions } from 'vuex'
    import WeightForm from "components/cargo/weightForm.vue";
    import UserForm from "components/cargo/userForm.vue";
    import CargoElement from "components/cargo/cargoElement.vue";
    export default {
        name: "cargo_managment",
        components: {CargoElement, UserForm, WeightForm},
        data(){
            return{
                cargo_id: '',
                items: ['By id', 'By amount'],
                choice: '',
            }
        },
        methods: {
            ...mapActions(['getAllCargos', 'findCargoById', 'findCargoByAmount', 'findCargoByWeightType', 'removeCargo']),
            find(){
                if (this.choice == "By id"){
                    this.findCargoById(this.cargo_id)
                }
                if (this.choice == "By amount"){
                    this.findCargoByAmount(this.cargo_id)
                }
                // if (this.choice == "By weight_value"){
                //     this.findCargoByWeightType(this.cargo_id)
                // }
                if (this.cargo_id == ''){
                    this.getAllCargos()
                }
            },
            deleteCargo(cargo){
                this.removeCargo(cargo)
                this.getAllCargos()
            }
        },
        computed: {
            ...mapState(['cargosAll']),
        },
        created(){
            this.getAllCargos()
        }
    }
</script>

<style scoped>

</style>