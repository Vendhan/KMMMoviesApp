//
//  DetailsScreen.swift
//  iosApp
//
//  Created by Arul Vendhan Arumugam on 02/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DetailsScreen: View {
    
    @ObservedObject private var viewmodel: DetailsScreenVM = DetailsScreenVM()
    
    init(movieID: Int) {
        viewmodel.viewModel.onIntent(intent: DetailsScreenSideEffects.GetMovieDetails(id: Int32(movieID)))
    }
    
    var body: some View {
        ZStack() {
            switch true {
            case viewmodel.movieState.error.isError:
                Text(viewmodel.movieState.error.errorMessage)
                
            case viewmodel.movieState.isSuccess:
                if(viewmodel.movieState.movies != nil){
                    DetailsScreenContent(movie: viewmodel.movieState.movies!)
                }
            case viewmodel.movieState.isLoading:
                ProgressView()
                
            default:
                Text("default")
            }
        }
    }
}
