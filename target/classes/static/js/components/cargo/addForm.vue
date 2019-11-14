<template>
    <v-container fluid>
        <div class="text--primary">
            Adding new cargo
        </div>
        <v-row align="center">
            <v-col lg="2">
                <v-text-field
                name="amount"
                label="Amount of cargo"
                v-model="amount"
                :error-messages="amountError"
                @input="$v.amount.$touch()"
                @blur="$v.amount.$touch()"
                @change="flagChange">
                </v-text-field>
            </v-col>
        </v-row>
        <v-row align="center">
            <v-col lg="2">
                <v-text-field
                name="weight_value"
                label="Weight of cargo"
                v-model="weight_value"
                :error-messages="weightValueError"
                @input="$v.weight_value.$touch()"
                @blur="$v.weight_value.$touch()"
                @change="flagChange">
                </v-text-field>
            </v-col>
            <v-col lg="2">
                <v-select
                label="Type of the weight"
                dense
                :items="weight_types"
                item-text="name"
                item-value="id"
                return-object
                v-model="weight_type"
                :error-messages="weight_typeValueError"
                @input="$v.weight_type.$touch()"
                @blur="$v.weight_type.$touch()">
                </v-select>
            </v-col>
        </v-row>
        <v-row align="center">
            <v-col lg="2">
                <v-text-field
                    name="length"
                    label="Length"
                    v-model="length"
                    :error-messages="lengthValueError"
                    @input="$v.length.$touch()"
                    @blur="$v.length.$touch()"
                    @change="flagChange">
                </v-text-field>
            </v-col>
        </v-row>
        <v-row align="center">
            <v-col lg="2">
                <v-text-field
                        name="width"
                        label="Width"
                        v-model="width"
                        :error-messages="widthValueError"
                        @input="$v.width.$touch()"
                        @blur="$v.width.$touch()"
                        @change="flagChange">
                </v-text-field>
            </v-col>
        </v-row>
        <v-row align="center">
            <v-col lg="2">
                <v-text-field
                        name="height"
                        label="Height"
                        v-model="height"
                        :error-messages="heightValueError"
                        @input="$v.height.$touch()"
                        @blur="$v.height.$touch()"
                        @change="flagChange">
                </v-text-field>
            </v-col>
        </v-row>
        <v-row align="center">
            <v-col lg="2">
                <v-select
                        label="Special conditions"
                        dense
                        :items="conditions"
                        item-text="name"
                        item-value="id"
                        return-object
                        multiple
                        attach
                        chips
                        v-model="condition">
                </v-select>
            </v-col>
        </v-row>
        <v-row align="center">
            <v-col lg="2">
                <v-select
                        label="Client"
                        dense
                        :items="clients"
                        item-text="username"
                        item-value="id"
                        return-object
                        v-model="client"
                        :error-messages="clientValueError"
                        @input="$v.client.$touch()"
                        @blur="$v.client.$touch()">
                </v-select>
            </v-col>
        </v-row>
        <v-row align="center">
            <v-col lg="2">
                <v-alert
                        class="mt-5"
                        :value="alert"
                        :color="alertColor"
                        dark
                        border="top"
                        transition="scale-transition">
                    {{alertMessage}}
                </v-alert>
            </v-col>
        </v-row>
        <v-row align="center">
            <v-col lg="2">
                <v-btn class="ma-9" color="light-blue" @click="create" :disabled="!flag">
                    Create new cargo
                </v-btn>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import {mapState} from 'vuex'
    import { mapActions } from 'vuex'
    import {validationMixin} from 'vuelidate'
    import {required, numeric} from 'vuelidate/lib/validators'
    export default {
        name: "addForm",
        mixins: [validationMixin],
        validations: {
            amount: {required, numeric},
            weight_value: {required, numeric},
            weight_type: {required},
            length: {required, numeric},
            width: {required, numeric},
            height: {required, numeric},
            client: {required}
        },
        data(){
            return{
                amount: '',
                weight_value: '',
                weight_type: '',
                length: '',
                width: '',
                height: '',
                condition: [],
                client: '',
                flag: false,
                alert: false,
                alertMessage: '',
                alertColor: '',
            }
        },
        methods: {
            ...mapActions(['getAllWeightTypes', 'getAllConditions', 'getAllClients', 'addNewCargo']),
            create(){
                this.$v.$touch()
                if (this.$v.$invalid){

                } else {
                    const cargo = {
                        amount: Number(this.amount),
                        weight: {
                            name: this.weight_type.name,
                            price: Number(this.weight_type.price)
                        },
                        weight_value: Number(this.weight_value),
                        length: Number(this.length),
                        width: Number(this.width),
                        height: Number(this.height),
                        special_conditions: this.condition,
                        user: this.client
                    }
                    if (this.addNewCargo(cargo)){
                        this.$router.push('/cargos_manager')
                    } else {
                        this.alertMessage = "Failed"
                        this.alertColor = "red"
                        this.alert = true
                    }
                }
            },
            flagChange(){
                if (this.amount == '' || this.weight_value == '' || this.length == '' || this.width == '' ||
                this.height == '') this.flag = false
                else this.flag = true
            }
        },
        computed: {
            ...mapState(['weight_types', 'conditions', 'clients']),
            amountError(){
                const errors = []
                if (!this.$v.amount.$dirty) return errors
                !this.$v.amount.required && errors.push('Enter the amount of cargo')
                !this.$v.amount.numeric && errors.push('Amount must consists of digits')
                this.flag = false
                return errors
            },
            weightValueError(){
                const errors = []
                if (!this.$v.weight_value.$dirty) return errors
                !this.$v.weight_value.required && errors.push('Enter the weight value of cargo')
                !this.$v.weight_value.numeric && errors.push('Weight value must consists of digits')
                this.flag = false
                return errors
            },
            lengthValueError(){
                const errors = []
                if (!this.$v.length.$dirty) return errors
                !this.$v.length.required && errors.push('Enter the length of cargo')
                !this.$v.length.numeric && errors.push('Length must consists of digits')
                this.flag = false
                return errors
            },
            weight_typeValueError(){
                const errors = []
                if (!this.$v.weight_type.$dirty) return errors
                !this.$v.weight_type.required && errors.push('Select the weight type')
                return errors
            },
            widthValueError(){
                const errors = []
                if (!this.$v.width.$dirty) return errors
                !this.$v.width.required && errors.push('Enter the width of cargo')
                !this.$v.width.numeric && errors.push('Width must consists of digits')
                this.flag = false
                return errors
            },
            heightValueError(){
                const errors = []
                if (!this.$v.height.$dirty) return errors
                !this.$v.height.required && errors.push('Enter the height of cargo')
                !this.$v.height.numeric && errors.push('Height must consists of digits')
                this.flag = false
                return errors
            },
            clientValueError(){
                const errors = []
                if (!this.$v.client.$dirty) return errors
                !this.$v.client.required && errors.push('Select the owner of the cargo')
                return errors
            }
        },
        created() {
            this.getAllWeightTypes()
            this.getAllConditions()
            this.getAllClients()
        }
    }
</script>

<style scoped>

</style>