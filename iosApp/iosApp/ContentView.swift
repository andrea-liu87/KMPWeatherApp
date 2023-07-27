import SwiftUI
import shared

struct ContentView: View {
    var body: some View {
        Color.black
            .ignoresSafeArea()
            .overlay(
                VStack {
                    Text("Montreal").foregroundColor(Color.white)
                        .font(.largeTitle)
                    Text("19°")
                        .foregroundColor(Color.white)
                        .font(.system(size: 96))
                    Text("Clear sky")
                        .foregroundColor(Color.white)
                        .font(.largeTitle)
        })
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
