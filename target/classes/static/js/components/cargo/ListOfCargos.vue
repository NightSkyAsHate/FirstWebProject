<template>
    <v-container fluid>
        <div class="text--primary">
            Cargos
        </div>
        <v-row>
            <v-col cols="6" sm="4">
                <v-container fluid>
                    <v-card class="mx-auto" v-for="cargo in cargos" :cargo="cargo" :key="cargo.id">

                    </v-card>
                </v-container>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>


    export default {
        name: "ListOfCargos",
        computed: mapState([''])
    }
</script>

<style scoped>

</style>