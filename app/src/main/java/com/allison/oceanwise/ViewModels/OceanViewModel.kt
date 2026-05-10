package com.allison.oceanwise.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allison.oceanwise.data.model.MarineAnimal
import com.allison.oceanwise.data.repository.OceanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OceanViewModel(private val repository: OceanRepository) : ViewModel() {

    private val _animals = MutableStateFlow<List<MarineAnimal>>(emptyList())
    val animals: StateFlow<List<MarineAnimal>> = _animals

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun fetchMarineAnimals() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val firestoreAnimals = repository.getMarineAnimals()
                if (firestoreAnimals.isNotEmpty()) {
                    _animals.value = firestoreAnimals
                } else {
                    _animals.value = getDefaultAnimals()
                }
            } catch (e: Exception) {
                _animals.value = getDefaultAnimals()
            } finally {
                _loading.value = false
            }
        }
    }

    private fun getDefaultAnimals(): List<MarineAnimal> {
        return listOf(
            MarineAnimal("1", "Great White Shark", "Carcharodon carcharias", "The world's largest known predatory fish.", "https://images.unsplash.com/photo-1560275619-4662e36fa65c?q=80&w=500", "Coastal and offshore waters", "Vulnerable", "Carnivore", "They can detect a drop of blood in 100 liters of water."),
            MarineAnimal("2", "Blue Whale", "Balaenoptera musculus", "The largest animal ever known to have lived on Earth.", "https://images.unsplash.com/photo-1568430462989-44163eb1752f?q=80&w=500", "All oceans except the Arctic", "Endangered", "Filter feeder", "Their tongue alone can weigh as much as an elephant."),
            MarineAnimal("3", "Green Sea Turtle", "Chelonia mydas", "Named for the greenish color of its cartilage and fat.", "https://images.unsplash.com/photo-1544551763-47a0159f963f?q=80&w=500", "Tropical and subtropical coastal waters", "Endangered", "Herbivore", "They are the only herbivorous sea turtle species."),
            MarineAnimal("4", "Common Bottlenose Dolphin", "Tursiops truncatus", "Highly intelligent marine mammals.", "https://images.unsplash.com/photo-1607335614551-3062bf90f30e?q=80&w=500", "Temperate and tropical oceans", "Least Concern", "Carnivore", "They use echolocation to find food."),
            MarineAnimal("5", "Giant Pacific Octopus", "Enteroctopus dofleini", "The largest and longest-lived species of octopus.", "https://images.unsplash.com/photo-1545671913-b89ac1b4ac10?q=80&w=500", "Northern Pacific Ocean", "Not Evaluated", "Carnivore", "They have three hearts and blue blood."),
            MarineAnimal("6", "Emperor Penguin", "Aptenodytes forsteri", "The tallest and heaviest of all living penguin species.", "https://images.unsplash.com/photo-1517783999520-f068d7431a60?q=80&w=500", "Antarctica", "Near Threatened", "Carnivore", "They can dive to depths of over 500 meters."),
            MarineAnimal("7", "Humpback Whale", "Megaptera novaeangliae", "Known for their complex songs and breaching behavior.", "https://images.unsplash.com/photo-1454991727061-be514eae86f7?q=80&w=500", "All major oceans", "Least Concern", "Filter feeder", "They migrate up to 25,000 km each year."),
            MarineAnimal("8", "Clownfish", "Amphiprioninae", "Small, brightly colored fish that live in sea anemones.", "https://images.unsplash.com/photo-1524704626871-3ee4ca292102?q=80&w=500", "Indian and Pacific Oceans", "Least Concern", "Omnivore", "They are born male and can change sex to become female."),
            MarineAnimal("9", "Manatee", "Trichechus", "Large, slow-moving aquatic mammals often called 'sea cows'.", "https://images.unsplash.com/photo-1549488344-cbb6c34ce097?q=80&w=500", "Rivers, estuaries, and coastal waters", "Vulnerable", "Herbivore", "They spend most of their time eating, sleeping, and traveling."),
            MarineAnimal("10", "Hammerhead Shark", "Sphyrnidae", "Distinctive sharks with hammer-shaped heads.", "https://images.unsplash.com/photo-1598977123418-458da9b3c514?q=80&w=500", "Tropical and warm temperate waters", "Critically Endangered", "Carnivore", "Their wide-set eyes give them better visual range than other sharks."),
            MarineAnimal("11", "Manta Ray", "Mobula birostris", "Large rays known for their triangular fins and horn-shaped cephalic fins.", "https://images.unsplash.com/photo-1583212292454-1fe6229603b7?q=80&w=500", "Tropical, subtropical and temperate oceans", "Vulnerable", "Filter feeder", "They have the largest brain-to-body ratio of any fish."),
            MarineAnimal("12", "Orca (Killer Whale)", "Orcinus orca", "The largest member of the oceanic dolphin family.", "https://images.unsplash.com/photo-1550133730-69542a55d72c?q=80&w=500", "All oceans from Arctic to Antarctic", "Data Deficient", "Carnivore", "Orcas are apex predators and have no natural enemies."),
            MarineAnimal("13", "Narwhal", "Monodon monoceros", "Medium-sized toothed whale with a large 'tusk' from a protruding canine tooth.", "https://images.unsplash.com/photo-1498623116890-37e912163d5d?q=80&w=500", "Arctic waters", "Least Concern", "Carnivore", "The tusk is actually a tooth that can grow up to 10 feet long."),
            MarineAnimal("14", "Seahorse", "Hippocampus", "Small marine fish with a head and neck suggestive of a horse.", "https://images.unsplash.com/photo-1530122037265-a5f1f91d3b99?q=80&w=500", "Tropical and temperate coastal waters", "Data Deficient", "Carnivore", "Male seahorses carry the eggs and give birth to the young."),
            MarineAnimal("15", "Loggerhead Turtle", "Caretta caretta", "Oceanic turtle named for its large head and powerful jaws.", "https://images.unsplash.com/photo-1437622368342-7a3d73a34c8f?q=80&w=500", "Atlantic, Pacific, and Indian Oceans", "Vulnerable", "Carnivore", "They have the largest hard shells in the world."),
            MarineAnimal("16", "Dugong", "Dugong dugon", "Medium-sized marine mammal, one of four living species of Sirenia.", "https://images.unsplash.com/photo-1634024329244-2457c1543315?q=80&w=500", "Indo-West Pacific coastal waters", "Vulnerable", "Herbivore", "They are closely related to elephants."),
            MarineAnimal("17", "Whale Shark", "Rhincodon typus", "The largest known extant fish species.", "https://images.unsplash.com/photo-1560275619-4662e36fa65c?q=80&w=500", "Tropical and warm-temperate oceans", "Endangered", "Filter feeder", "Each whale shark has a unique pattern of spots, like a human fingerprint."),
            MarineAnimal("18", "Lionfish", "Pterois", "Venomous marine fish with distinctive red, white, creamy, or black bands.", "https://images.unsplash.com/photo-1544551763-8dd44758c2dd?q=80&w=500", "Indo-Pacific and invasive in Atlantic", "Least Concern", "Carnivore", "Their venomous spines are used purely for defense."),
            MarineAnimal("19", "Beluga Whale", "Delphinapterus leucas", "Arctic and sub-Arctic cetacean known for its white color.", "https://images.unsplash.com/photo-1544923246-77307dd654ca?q=80&w=500", "Arctic and sub-Arctic waters", "Least Concern", "Carnivore", "They are often called 'sea canaries' because of their high-pitched whistles."),
            MarineAnimal("20", "Leafy Seadragon", "Phycodurus eques", "Marine fish related to the seahorse, with long leaf-like protrusions.", "https://images.unsplash.com/photo-1530122037265-a5f1f91d3b99?q=80&w=500", "South and west coasts of Australia", "Near Threatened", "Carnivore", "Their protrusions are not used for movement, only for camouflage."),
            MarineAnimal("21", "Box Jellyfish", "Cubozoa", "Jellyfish with a cube-shaped medusa.", "https://images.unsplash.com/photo-1544551763-47a0159f963f?q=80&w=500", "Tropical Indo-Pacific", "Not Evaluated", "Carnivore", "They have 24 eyes and some of the most deadly venom in the world."),
            MarineAnimal("22", "Moorish Idol", "Zanclus cornutus", "Small marine fish known for its long, white-tipped dorsal fin.", "https://images.unsplash.com/photo-1524704626871-3ee4ca292102?q=80&w=500", "Indo-Pacific coral reefs", "Not Evaluated", "Omnivore", "They get their name from the Moors of Africa, who purportedly believed the fish brought happiness."),
            MarineAnimal("23", "Vampire Squid", "Vampyroteuthis infernalis", "Deep-sea cephalopod that looks like a cross between a squid and an octopus.", "https://images.unsplash.com/photo-1545671913-b89ac1b4ac10?q=80&w=500", "Tropical and temperate deep oceans", "Not Evaluated", "Detritivore", "It doesn't drink blood; it eats 'marine snow' (organic detritus)."),
            MarineAnimal("24", "Stingray", "Myliobatoidei", "Group of rays related to sharks, with a venomous tail stinger.", "https://images.unsplash.com/photo-1583212292454-1fe6229603b7?q=80&w=500", "Tropical and subtropical coastal waters", "Various", "Carnivore", "They have no bones; their skeletons are made of cartilage."),
            MarineAnimal("25", "Blue-Ringed Octopus", "Hapalochlaena", "Small but deadly octopus with characteristic blue rings.", "https://images.unsplash.com/photo-1545671913-b89ac1b4ac10?q=80&w=500", "Pacific and Indian Oceans", "Not Evaluated", "Carnivore", "One octopus carries enough venom to kill 26 adult humans within minutes."),
            MarineAnimal("26", "Anglerfish", "Lophiiformes", "Deep-sea fish known for the bioluminescent lure on its head.", "https://images.unsplash.com/photo-1545671913-b89ac1b4ac10?q=80&w=500", "Deep Atlantic and Antarctic Oceans", "Not Evaluated", "Carnivore", "The males are much smaller and live as parasites on the females."),
            MarineAnimal("27", "Leatherback Turtle", "Dermochelys coriacea", "The largest of all living turtles.", "https://images.unsplash.com/photo-1544551763-47a0159f963f?q=80&w=500", "Open ocean and nesting on beaches", "Vulnerable", "Carnivore", "They have a rubbery, leathery skin instead of a hard shell."),
            MarineAnimal("28", "Walrus", "Odobenus rosmarus", "Large flippered marine mammal with prominent tusks and whiskers.", "https://images.unsplash.com/photo-1549488344-cbb6c34ce097?q=80&w=500", "Arctic Circle and sub-Arctic seas", "Vulnerable", "Carnivore", "Their tusks can grow up to 3 feet long and are used for pulling themselves out of water."),
            MarineAnimal("29", "Barracuda", "Sphyraena", "Large, predatory ray-finned fish known for its fearsome appearance.", "https://images.unsplash.com/photo-1560275619-4662e36fa65c?q=80&w=500", "Tropical and subtropical oceans", "Least Concern", "Carnivore", "They are capable of bursts of speed up to 27 mph."),
            MarineAnimal("30", "Moray Eel", "Muraenidae", "Cosmopolitan family of eels.", "https://images.unsplash.com/photo-1524704626871-3ee4ca292102?q=80&w=500", "Tropical and temperate seas", "Least Concern", "Carnivore", "They have a second set of jaws in their throat called pharyngeal jaws."),
            MarineAnimal("31", "Sperm Whale", "Physeter macrocephalus", "The largest of the toothed whales.", "https://images.unsplash.com/photo-1568430462989-44163eb1752f?q=80&w=500", "Ice-free waters of all oceans", "Vulnerable", "Carnivore", "They have the largest brain of any animal on Earth."),
            MarineAnimal("32", "Bull Shark", "Carcharhinus leucas", "Shark known for its aggressive nature and ability to live in fresh water.", "https://images.unsplash.com/photo-1560275619-4662e36fa65c?q=80&w=500", "Warm, shallow coastal and river waters", "Vulnerable", "Carnivore", "They are one of the few shark species that can thrive in both saltwater and freshwater."),
            MarineAnimal("33", "Flying Fish", "Exocoetidae", "Marine fish that can make powerful, self-propelled leaps out of water.", "https://images.unsplash.com/photo-1524704626871-3ee4ca292102?q=80&w=500", "Tropical and subtropical oceans", "Least Concern", "Planktivore", "They can glide for distances of up to 200 meters."),
            MarineAnimal("34", "Cuttlefish", "Sepiida", "Marine mollusks related to the squid and octopus.", "https://images.unsplash.com/photo-1545671913-b89ac1b4ac10?q=80&w=500", "Coastal waters and reefs", "Least Concern", "Carnivore", "They have an internal shell called a cuttlebone, which is used for buoyancy."),
            MarineAnimal("35", "Spider Crab", "Majoidea", "Crabs with long, spindly legs.", "https://images.unsplash.com/photo-1545671913-b89ac1b4ac10?q=80&w=500", "All oceans", "Not Evaluated", "Omnivore", "The Japanese spider crab has the largest leg span of any arthropod, up to 12 feet."),
            MarineAnimal("36", "Leopard Seal", "Hydrurga leptonyx", "Large earless seal and the second largest species of seal in the Antarctic.", "https://images.unsplash.com/photo-1544923246-77307dd654ca?q=80&w=500", "Antarctic waters", "Least Concern", "Carnivore", "They are the only seals that regularly feed on warm-blooded prey, like other seals and penguins."),
            MarineAnimal("37", "Coral Polyp", "Anthozoa", "Tiny, soft-bodied organisms related to sea anemones and jellyfish.", "https://images.unsplash.com/photo-1546026423-cc4642628f2b?q=80&w=500", "Clear, shallow tropical waters", "Endangered (some species)", "Carnivore", "They build the limestone structures that form coral reefs."),
            MarineAnimal("38", "Sawfish", "Pristidae", "Rays characterized by a long, narrow, flattened rostrum lined with sharp teeth.", "https://images.unsplash.com/photo-1560275619-4662e36fa65c?q=80&w=500", "Tropical and subtropical coastal waters", "Critically Endangered", "Carnivore", "The saw is used for both sensing and attacking prey."),
            MarineAnimal("39", "Lobster", "Nephropidae", "Family of large marine crustaceans.", "https://images.unsplash.com/photo-1559737558-2f5a35f4523b?q=80&w=500", "All oceans, mostly on rocky, sandy, or muddy bottoms", "Least Concern", "Omnivore", "Lobsters can live to be over 100 years old."),
            MarineAnimal("40", "Swordfish", "Xiphias gladius", "Large, highly migratory, predatory fish characterized by a long, flat bill.", "https://images.unsplash.com/photo-1560275619-4662e36fa65c?q=80&w=500", "Tropical and temperate oceans", "Least Concern", "Carnivore", "They are among the fastest fish in the ocean, reaching speeds of 60 mph."),
            MarineAnimal("41", "Parrotfish", "Scaridae", "Colorful fish known for their beak-like teeth.", "https://images.unsplash.com/photo-1524704626871-3ee4ca292102?q=80&w=500", "Tropical coral reefs", "Least Concern", "Herbivore", "They excrete sand as a byproduct of eating coral and algae."),
            MarineAnimal("42", "Sea Otter", "Enhydra lutris", "Marine mammal native to the coasts of the northern and eastern North Pacific Ocean.", "https://images.unsplash.com/photo-1517783999520-f068d7431a60?q=80&w=500", "Coastal North Pacific", "Endangered", "Carnivore", "They hold hands while sleeping to keep from drifting apart."),
            MarineAnimal("43", "Pufferfish", "Tetraodontidae", "Fish that can inflate themselves into a ball shape for defense.", "https://images.unsplash.com/photo-1524704626871-3ee4ca292102?q=80&w=500", "Tropical and subtropical oceans", "Least Concern", "Omnivore", "Most pufferfish contain tetrodotoxin, which is 1,200 times more poisonous than cyanide."),
            MarineAnimal("44", "Frilled Shark", "Chlamydoselachus anguineus", "Rare deep-sea shark with primitive features.", "https://images.unsplash.com/photo-1560275619-4662e36fa65c?q=80&w=500", "Atlantic and Pacific deep waters", "Least Concern", "Carnivore", "It is often called a 'living fossil' because it has changed very little for millions of years."),
            MarineAnimal("45", "Sunfish (Mola Mola)", "Mola mola", "The heaviest known bony fish in the world.", "https://images.unsplash.com/photo-1524704626871-3ee4ca292102?q=80&w=500", "Temperate and tropical oceans", "Vulnerable", "Carnivore", "A single female can produce up to 300 million eggs at a time."),
            MarineAnimal("46", "Garden Eel", "Heterocongrinae", "Small eels that live in burrows on the sea floor.", "https://images.unsplash.com/photo-1524704626871-3ee4ca292102?q=80&w=500", "Indo-Pacific and Atlantic", "Least Concern", "Planktivore", "They live in large colonies that look like a garden of waving grass."),
            MarineAnimal("47", "Ribbon Eel", "Rhinomuraena quaesita", "Species of moray eel known for its long, thin body and bright colors.", "https://images.unsplash.com/photo-1524704626871-3ee4ca292102?q=80&w=500", "Indo-Pacific", "Least Concern", "Carnivore", "They change color and gender as they mature."),
            MarineAnimal("48", "Stonefish", "Synanceia", "The most venomous fish known.", "https://images.unsplash.com/photo-1524704626871-3ee4ca292102?q=80&w=500", "Indo-Pacific coastal waters", "Least Concern", "Carnivore", "They are masters of camouflage, looking exactly like a encrusted stone or piece of coral."),
            MarineAnimal("49", "Electric Eel", "Electrophorus electricus", "Though called an eel, it's actually a knifefish that can produce electricity.", "https://images.unsplash.com/photo-1544923246-77307dd654ca?q=80&w=500", "Freshwater (Amazon and Orinoco basins)", "Least Concern", "Carnivore", "They can generate shocks of over 600 volts to stun prey."),
            MarineAnimal("50", "Giant Squid", "Architeuthis dux", "Deep-ocean dwelling squid that can grow to tremendous sizes.", "https://images.unsplash.com/photo-1545671913-b89ac1b4ac10?q=80&w=500", "Deep oceans worldwide", "Least Concern", "Carnivore", "They have the largest eyes in the animal kingdom, about the size of a dinner plate.")
        )
    }
}
