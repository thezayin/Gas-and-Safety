package com.thezayin.lpg.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.thezayin.presentation.AddressScreen
import com.thezayin.presentation.CartScreen
import com.thezayin.presentation.HistoryScreen
import com.thezayin.presentation.HomeScreen
import com.thezayin.presentation.OrderScreen
import com.thezayin.presentation.ProductScreen
import com.thezayin.presentation.ProfileScreen
import com.thezayin.setting.ContactScreen
import com.thezayin.setting.SettingScreen
import com.thezayin.splash.SplashScreen

@Composable
fun NavHost(navController: NavHostController) {
    androidx.navigation.compose.NavHost(
        navController = navController, startDestination = SplashScreenNav
    ) {
        composable<SplashScreenNav> {
            SplashScreen(onSplashFinished = {
                navController.navigate(HomeScreenNav)
            })
        }

        composable<HomeScreenNav> {
            HomeScreen(
                navigateToCart = {
                    navController.navigate(CartScreenNav)
                }, navigateToProfile = {
                    navController.navigate(ProfileScreenNav)
                }, navigateToAddress = {
                    navController.navigate(AddressScreenNav)
                }, navigateToSetting = {
                    navController.navigate(SettingScreenNav)
                }, navigateToContact = {
                    navController.navigate(ContactUsScreenNav)
                }, navigateToHistory = {
                    navController.navigate(HistoryScreenNav)
                },
                navigateToProductScreen = { productId ->
                    navController.navigate(ProductScreenNav(productId))
                }
            )
        }

        composable<SettingScreenNav> {
            SettingScreen(
                navigateUp = { navController.navigateUp() },
                navigateToContactUs = { navController.navigate(ContactUsScreenNav) }
            )
        }

        composable<ContactUsScreenNav> {
            ContactScreen(navigateUp = { navController.navigateUp() })
        }

        composable<CartScreenNav> {
            CartScreen(onBackClick = { navController.navigateUp() }, onContactClick = {
                navController.navigate(ContactUsScreenNav)
            }, onBuyClick = {
                navController.navigate(OrderScreenNav)
            })
        }

        composable<AddressScreenNav> {
            AddressScreen(
                navigateBack = { navController.navigateUp() },
                navigateToContactUs = {
                    navController.navigate(ContactUsScreenNav)
                },
                navigateToProfile = {
                    navController.navigate(ProfileScreenNav)
                },
            )
        }
        composable<ProfileScreenNav> {
            ProfileScreen(
                navigateBack = {
                    navController.navigateUp()
                },
                navigateToContactUs = {
                    navController.navigate(ContactUsScreenNav)
                },
                navigateToAddress = {
                    navController.navigate(AddressScreenNav)
                },
            )
        }

        composable<OrderScreenNav> {
            OrderScreen(navigateUp = { navController.navigateUp() }, navigateToHome = {
                navController.popBackStack(HomeScreenNav, false)
            }, navigateToProfile = {
                navController.navigate(ProfileScreenNav)
            }, navigateToContactUs = {
                navController.navigate(ContactUsScreenNav)
            })
        }

        composable<HistoryScreenNav> {
            HistoryScreen(
                navigateUp = { navController.navigateUp() },
                navigateToContactUs = { navController.navigate(ContactUsScreenNav) }
            )
        }

        composable<ProductScreenNav> {
            val args = it.toRoute<ProductScreenNav>()
            ProductScreen(
                productId = args.productId,
                navigateUp = { navController.navigateUp() },
                navigateToContact = { navController.navigate(ContactUsScreenNav) }
            )
        }
    }
}