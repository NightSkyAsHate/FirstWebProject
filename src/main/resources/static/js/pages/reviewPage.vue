<template>
    <v-container fluid>
        <v-row justify="center" style="font-size: 50px">
            Client's reviews
        </v-row>
        <v-row>
            <v-col cols="6" sm="4">
                <v-container fluid>
                    <v-card class="mx-auto" v-for="review in reviews" :review="review" :key="review.id">
                        <v-card-text>
                            <div>{{ review.localDate }}</div>
                            <p class="display-1 text--primary">
                                {{ review.username }}
                            </p>
                            <div class="text--primary">
                                {{ review.message }}
                            </div>
                        </v-card-text>
                    </v-card>
                </v-container>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="6" sm="30">
                <v-container fluid v-if="profile">
                    <v-row>
                        <v-col cols="10" md="6">
                            <v-textarea v-model="review"></v-textarea>
                        </v-col>
                    </v-row>
                    <v-row class="d-flex flex-lg-grow-0">
                        <v-col cols="10" md="6">
                            <v-btn color="light-blue" dark @click="addReview">
                                Send review
                            </v-btn>
                        </v-col>
                    </v-row>
                </v-container>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import { mapState } from 'vuex'
    import { mapActions } from 'vuex'

    export default {
        name: "reviewPage",
        computed: mapState(['reviews', 'profile', 'user']),
        data(){
            return{
                review: ''
            }
        },
        methods: {
            ...mapActions(['addReviewAction']),
            addReview() {
                const rev = {
                    "message": this.review,
                    "username": this.profile.username
                };

                this.addReviewAction(rev)
                this.review = ''
            }
        }
    }
</script>

<style scoped>

</style>