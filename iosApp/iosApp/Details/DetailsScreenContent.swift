//
//  DetailsScreenContent.swift
//  iosApp
//
//  Created by Arul Vendhan Arumugam on 02/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared
import CachedAsyncImage

struct DetailsScreenContent: View {
    
    var movie : MoviesResult
    
    var body: some View {
        
        ScrollView {
            VStack(alignment: HorizontalAlignment.center) {
                if #available(iOS 15.0, *) {
                    let url = URL(string: movie.posterPath ?? "")
                    CachedAsyncImage(
                        url: url,
                        content: { image in
                            image.resizable()
                                .aspectRatio(contentMode: .fill)
                                .frame(width: .infinity, height: 350)
                                .clipShape(RoundedRectangle(cornerRadius: 16))
                                .padding(16)

                        },
                        placeholder: {
                            ZStack {
                                Text("Loading..")
                                    .font(.system(size: 16, weight: .medium))
                            }
                        }
                    )
                }
                
                Spacer()
                    .frame(height: 8)
                
                Text(movie.title)
                    .font(.system(size: 18, weight: .bold))
                
                Spacer()
                    .frame(height: 8)
                
                Text(movie.overview)
                    .font(.system(size: 16, weight: .medium))
                    .previewDisplayName("OverView")
            }
            .padding(16)
            .frame(width: .infinity, height: .infinity, alignment: Alignment.top)
        }
    }
}
